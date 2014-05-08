package com.onboard.plugin.git.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.MergeCommand.FastForwardMode;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.merge.Merger;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;
import org.eclipse.jgit.util.io.DisabledOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GitOperation {

    public static final Logger logger = LoggerFactory.getLogger(GitOperation.class);

    @Value("#{systemProperties['user.home'].concat('/repositories/')}")
    private String gitRepoPath;

    private String getRepositoryPathInFS(int id) {
        return String.format("%s%d.git", gitRepoPath, id);
    }

    public void createRepository(int id) {
        File f = new File(getRepositoryPathInFS(id));
        Git git = null;
        try {
            git = Git.init().setBare(true).setDirectory(f).call();
        } catch (GitAPIException e) {
            logger.info("repository creation failed", e);
            throw new RuntimeException(e);
        }
        Repository r = git.getRepository();
        try {
            StoredConfig config = r.getConfig();
            config.setBoolean("http", null, "receivepack", true);
            config.save();
        } catch (Exception e) {
            logger.info("get repository after creation failed", e);
            throw new RuntimeException(e);
        } finally {
            if (r != null) {
                r.close();
            }
        }

    }

    public Iterable<RevCommit> getCommits(Repository r, String since, String until) {
        return getCommitsByStartLimit(r, since, until, -1, -1);
    }

    public Iterable<RevCommit> getCommitsByStartLimit(Repository r, String since, String until, int start, int limit) {

        Iterable<RevCommit> commits = null;
        try {
            Git git = new Git(r);

            // 添加commits的分页功能
            LogCommand command = git.log();
            command.setMaxCount(limit);
            command.setSkip(start);

            if (since == null) {
                commits = command.add(r.resolve(until)).call();
            } else {
                commits = command.addRange(r.resolve(since), r.resolve(until)).call();
            }
            r.close();
        } catch (Exception e) {
            logger.info(e.getClass().getName(), e);
            throw new RuntimeException(e);
        }
        return commits;
    }

    public List<Ref> getBranches(Repository r) {
        List<Ref> branches = null;
        try {
            Git git = new Git(r);
            branches = git.branchList().call();
        } catch (Exception e) {
            logger.info(e.getClass().getName(), e);
            throw new RuntimeException(e);
        }

        return branches;
    }

    public List<Ref> getTags(Repository r) {
        List<Ref> tags = null;
        try {
            Git git = new Git(r);
            tags = git.tagList().call();
        } catch (GitAPIException e) {
            logger.info(e.getClass().getName(), e);
            throw new RuntimeException(e);
        }

        return tags;
    }

    public int getPathType(Repository r, String rev, String path) {
        int result = 0;
        try {
            ObjectId objectId = r.resolve(String.format("%s:%s", rev, path));
            RevWalk rw = new RevWalk(r);
            RevObject obj = rw.parseAny(objectId);
            result = obj.getType();
        } catch (Exception e) {
            logger.info(e.getClass().getName(), e);
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<DiffEntry> getDiffOfCommit(Repository repository, RevWalk revWalk, RevCommit commit)
            throws MissingObjectException, IOException {
        DiffFormatter diffFmt = new DiffFormatter(DisabledOutputStream.INSTANCE);
        diffFmt.setRepository(repository);
        if (commit.getParentCount() >= 1) {
            RevCommit parent = commit.getParent(0);
            revWalk.parseHeaders(parent);
            logger.debug("{}", parent);
            logger.debug("{}", parent.getFullMessage());
            return diffFmt.scan(parent.getTree(), commit.getTree());
        }
        return diffFmt.scan(new EmptyTreeIterator(), new CanonicalTreeParser(null, revWalk.getObjectReader(), commit.getTree()));
    }

    public boolean canMerge(Repository repository, String a, String b) throws IOException {
        ObjectId srcId = repository.resolve(a);
        ObjectId destId = repository.resolve(b);
        // using RECURSIVE strategy to merge in memory (because the repos is bare.
        Merger merger = MergeStrategy.RECURSIVE.newMerger(repository, true);
        return merger.merge(srcId, destId);
    }

    public void merge(Repository repository, String src, String dest) throws InvalidRemoteException, TransportException,
            GitAPIException, IOException {
        File localPath = File.createTempFile("git", "");
        localPath.delete();
        logger.debug("src = {} dest = {}", src, dest);
        logger.debug("start = {}", localPath.getAbsolutePath());
        Git git = Git.cloneRepository().setURI(repository.getDirectory().getAbsolutePath()).setDirectory(localPath)
                .setNoCheckout(true).call();
        Repository r = git.getRepository();
        logger.debug("end = {}", r.getDirectory().getAbsolutePath());
        git.checkout().setCreateBranch(true).setName(dest).setUpstreamMode(SetupUpstreamMode.TRACK)
                .setStartPoint("origin/" + dest).call();
        MergeResult result = git.merge().setCommit(false).setStrategy(MergeStrategy.RECURSIVE)
                .setFastForward(FastForwardMode.NO_FF).include(r.getRef("origin/" + src)).call();
        logger.debug("result = {}", result);
        if (result.getMergeStatus().isSuccessful()) {
            git.commit().setMessage("Merge PullRequest #").call();
        }

        Git fetch = new Git(repository);
        FetchResult fr = fetch.fetch().setRemote(r.getDirectory().getAbsolutePath())
                .setRefSpecs(new RefSpec("HEAD:refs/heads/" + dest)).call();

        logger.debug("fetch result = {}", fr.getMessages());
        FileUtils.deleteQuietly(localPath);
    }

}
