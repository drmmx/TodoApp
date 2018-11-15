package com.drmmx.devmax.todoapp.model;

import weborb.service.MapToProperty;

public class Todo {
    private String objectId;
    private String name;
    private String description;
    @MapToProperty( property = "checked" )
    private boolean checked = false;

    public Todo(String name, String description, boolean checked) {
        this.name = name;
        this.description = description;
        this.checked = checked;
    }

    public Todo() {
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
