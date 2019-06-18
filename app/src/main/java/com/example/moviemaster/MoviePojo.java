package com.example.moviemaster;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity
class MoviePojo {
    @PrimaryKey@NonNull
    String id;
    String image;
    String title;
    String avg;
    String over;
    String release;
public  MoviePojo(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public MoviePojo(String image, String title, String avg, String over, String release, @NonNull String id) {
        this.image = image;
        this.title = title;
        this.avg = avg;
        this.over = over;
        this.release = release;
        this.id = id;
    }
}
