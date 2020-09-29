package com.saidmorales.movies.services;

import com.saidmorales.movies.data.MovieRepository;
import com.saidmorales.movies.model.Genre;
import com.saidmorales.movies.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findByGenre(Genre genre) {
        return movieRepository.findAll().stream(  )
                .filter( movie -> movie.getGenre() == genre )
                .collect(Collectors.toList());
    }

    public Collection<Movie> findByDuration(Integer duration) {
        return movieRepository.findAll().stream()
                .filter(movie -> {return (movie.getDuration() >= duration);})
                .collect(Collectors.toList());
    }
}
