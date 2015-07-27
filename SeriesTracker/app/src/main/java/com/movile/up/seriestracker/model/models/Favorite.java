package com.movile.up.seriestracker.model.models;

/**
 * Created by root on 27/07/15.
 */
public class Favorite {

    private String slug;
    private String title;

    public Favorite(String slug, String title) {
        this.slug = slug;
        this.title = title;
    }

    public String getSlug() {
        return slug;    }

    public String getTitle() {
        return title;
    }
}
