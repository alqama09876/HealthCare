package com.example.healthcare.PojoClasses;

public class InformationHealth {
    private String title;
    private String description;

    public InformationHealth(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
