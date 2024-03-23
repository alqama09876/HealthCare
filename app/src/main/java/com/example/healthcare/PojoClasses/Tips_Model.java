package com.example.healthcare.PojoClasses;

public class Tips_Model {
    private String title;
    private String subtitle;

    public Tips_Model(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
