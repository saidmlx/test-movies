package com.saidmorales.movies.model;

import java.util.Objects;

public class Movie {

    private Integer id;
    private String name;
    private Integer duration;
    private Genre genre;

    public Movie(String name, Integer duration, Genre genre) {
        this(null, name, duration,genre);
    }

    public Movie() {
    }

    public Movie(Integer id, String name, Integer duration, Genre genre) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(name, movie.name) &&
                Objects.equals(duration, movie.duration) &&
                genre == movie.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, genre);
    }
}
