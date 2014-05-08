package com.onboard.plugin.git.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;

public class FileTree {

    private String data;

    private Set<FileTree> children;
    
    private ChangeType type;

    public FileTree(String data) {
        this.data = data;
        this.children = new LinkedHashSet<FileTree>();
        this.type = null;
    }

    public void insert(String path, ChangeType type) {
        String[] tmp = path.split("/", 2);
        FileTree node = getChild(tmp[0]);
        if (tmp.length > 1) {
            node.insert(tmp[1], type);
        } else {
            node.setType(type);
        }
    }

    public FileTree getChild(String data) {
        for (FileTree child : this.children) {
            if (data.equals(child.getData())) {
                return child;
            }
        }

        return getChild(new FileTree(data));
    }

    public FileTree getChild(FileTree child) {
        this.children.add(child);
        return child;
    }

    @JsonProperty("name")
    public String getData() {
        return this.data;
    }

    @JsonSerialize(include = Inclusion.NON_NULL)
    public List<FileTree> getChildren() {
        if (this.children.isEmpty()) {
            return null;
        }
        return new ArrayList<FileTree>(this.children);
    }

    @JsonProperty("status")
    @JsonSerialize(include = Inclusion.NON_NULL)
    public ChangeType getType() {
        return type;
    }

    public void setType(ChangeType type) {
        this.type = type;
    }
}
