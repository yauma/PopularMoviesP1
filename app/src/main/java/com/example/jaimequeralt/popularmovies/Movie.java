package com.example.jaimequeralt.popularmovies;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jaimequeralt on 9/15/15.
 */
public class Movie implements Serializable{

    private String title, poster_path, overview, releaseDate;
    private float rating;

    public Movie() {

    }

    public Movie(String title, String poster_path, String overview, String releaseDate, float rating) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
