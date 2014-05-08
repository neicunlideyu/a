package com.onboard.plugin.git.utils;

import java.io.IOException;
import java.util.Date;

import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onboard.plugin.git.model.BasicCommit;

@Service
public class CommitUtils {

    private CommonUtil commonUtil;

    @Autowired
    public void setCommonUtil(CommonUtil commonUtil) {
		this.commonUtil = commonUtil;
	}

	public static Logger logger = LoggerFactory.getLogger(CommitUtils.class);

    public static BasicCommit commitToMap(RevCommit commit) {
        String first = null;
        String second = null;
        if (commit.getParentCount() >= 1) {
            first = commit.getParent(0).name();
        }
        if (commit.getParentCount() == 2) {
            second = commit.getParent(1).name();
        }
        logger.debug("{} {}", first, second);
        return new BasicCommit(commit.getId().getName(), first, second, commit.getAuthorIdent().getName(), commit
                .getAuthorIdent().getEmailAddress(), commit.getShortMessage(), commit.getFullMessage(), new Date(
                ((long) commit.getCommitTime()) * 1000));
    }
    
    /**
     * get merge base for two commits
     * @param repoId
     * @param commitAId
     * @param commitBId
     * @return
     */
    public RevCommit getBaseCommit(int repoId, ObjectId commitAId, ObjectId commitBId){
        RevCommit base = null;
        try {
            //get repository
            org.eclipse.jgit.lib.Repository repository = commonUtil.getRepository(repoId);
            RevWalk revWalk = new RevWalk(repository);
			//get commit object
	        RevCommit sourceLastCommit = revWalk.parseCommit(commitAId);
	        RevCommit destinationLastCommit = revWalk.parseCommit(commitBId);
	        //copy from @link{org.eclipse.jgit.merge.Merger} getBaseCommit as it is a protected function
	        revWalk.reset();
	        revWalk.setRevFilter(RevFilter.MERGE_BASE);
			revWalk.markStart(destinationLastCommit);
			revWalk.markStart(sourceLastCommit);
			base = revWalk.next();
		} catch (MissingObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IncorrectObjectTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return base;
    }
}
