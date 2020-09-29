package com.saidmorales.movies.data;

import com.saidmorales.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {
    Movie findById(Integer id);
    Collection<Movie> findAll();
    void SaveOrUpdate(Movie movie);
}
