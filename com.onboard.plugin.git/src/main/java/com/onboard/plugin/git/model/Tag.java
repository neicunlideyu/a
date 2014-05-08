package com.onboard.plugin.git.model;

public class Tag {

    private String id;
    private String refName;
    private String displayName;

    
    public Tag(String id, String refName) {
        this.id = id;
        this.refName = refName;
        String[] tmp = refName.split("\\/", 3);
        this.displayName = tmp[2];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
