package com.onboard.plugin.git.model;

public class Item {

    private int type;


    private String name;

    public int getType() {
        return type;
    }

    public Item(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
