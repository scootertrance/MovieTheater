package com.softserve.academy.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private List<String> genres;
    private String description;
    private List<String> directors;
    private List<String> cast;
    private int rating;
    private int duration;

    public Movie(int id, String title, List<String> genres, String description, List<String> directors,
                 List<String> cast, int rating, int duration) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.description = description;
        this.directors = directors;
        this.cast = cast;
        this.rating = rating;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getCast() {
        return cast;
    }

    public int getRating() {
        return rating;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", directors=" + directors +
                ", cast=" + cast +
                ", rating=" + rating +
                ", duration=" + duration +
                '}';
    }
}
