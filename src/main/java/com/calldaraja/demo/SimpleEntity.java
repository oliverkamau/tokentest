package com.calldaraja.demo;

public class SimpleEntity {
    protected String name;

    public SimpleEntity(String name) {
        this.name = name;
    }

    public SimpleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
