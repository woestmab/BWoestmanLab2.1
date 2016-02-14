package com.woestmanBrian.bWoestmanLab2_1.fragments;

/**
 * Created by Brian Woestman on 2/6/16.
 * Android Programming
 * We 5:30p - 9:20p
 */
public class Ipsum {

    private String title;
    private String content;

    public Ipsum() {
    }

    public Ipsum(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
