package com.onboard.plugin.git.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.onboard.plugin.git.model.FileTree;

@Service
public class CommonUtil {

    public static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    @Value("#{systemProperties['user.home'].concat('/repositories/')}")
    private String gitRepoPath;

    public String parseJsonTree(List<DiffEntry> diffs) throws JsonGenerationException, JsonMappingException, IOException {
        FileTree root = new FileTree("");
        for (DiffEntry entry : diffs) {
            logger.debug("change type = {}", entry.getChangeType());
            root.insert(entry.getNewPath(), entry.getChangeType());
        }

        return new ObjectMapper().writeValueAsString(root.getChildren());
    }

    public org.eclipse.jgit.lib.Repository getRepository(int repoId) throws IOException {
        String path = getRepositoryPath(repoId);
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        File f = new File(path);

        return builder.setGitDir(f).readEnvironment().findGitDir().build();
    }

    public String getRepositoryPath(int repoId) {
        return String.format("%s%d.git", gitRepoPath, repoId);
    }

    public int getRepoIdByPaht(String path) {
        path = path.substring(this.gitRepoPath.length(), path.indexOf(".git"));
        return Integer.parseInt(path);
    }

}
