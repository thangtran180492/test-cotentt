package com.example.asm3animal.model;

import android.graphics.Bitmap;

public class Animal {private final Bitmap photo;

    private final Bitmap photoBg;

    private final String path;

    private final String name;

    private final String content;

    private boolean isFav;

    public Animal(Bitmap photo, Bitmap photoBg, String path, String name, String content, boolean isFav) {
        this.photo = photo;
        this.photoBg = photoBg;
        this.path = path;
        this.name = name;
        this.content = content;
        this.isFav = isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public Bitmap getPhotoBg() {
        return photoBg;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public boolean isFav() {
        return isFav;
    }
}
