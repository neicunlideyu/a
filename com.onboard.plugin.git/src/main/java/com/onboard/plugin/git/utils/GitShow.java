package com.onboard.plugin.git.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;

public class GitShow {

    public static final String CHARSET = "UTF-8";

    public static List<String> show(Repository r, RevTree tree)
            throws MissingObjectException, IncorrectObjectTypeException,
            CorruptObjectException, IOException {
        List<String> list = new ArrayList<String>();
        final TreeWalk walk = new TreeWalk(r);
        walk.reset();
        walk.addTree(tree);

        while (walk.next()) {
            StringBuilder sb = new StringBuilder(walk.getPathString());
            if (walk.getFileMode(0) == FileMode.TREE) {
                sb.append('/');
            }
            list.add(sb.toString());
        }

        return list;
    }

    public static String show(Repository r, RevObject obj)
            throws MissingObjectException, IncorrectObjectTypeException,
            IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        r.open(obj, obj.getType()).copyTo(out);

        return out.toString(CHARSET);
    }
}
