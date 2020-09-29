package com.saidmorales.movies.services;

import com.saidmorales.movies.data.MovieRepository;
import com.saidmorales.movies.model.Genre;
import com.saidmorales.movies.model.Movie;
import junit.framework.TestCase;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceTest {

    private MovieRepository movieRepository;
    private MovieService movieService;
    @Before
    public void setUp() throws Exception {
        movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento",113, Genre.THRILLER),
                        new Movie(3, "There's something About Mary", 119, Genre.COMEDY),
                        new Movie(4, "Super 8",112 , Genre.THRILLER),
                        new Movie(5, "Scream", 11, Genre.HORROR),
                        new Movie(6, "Home alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 136, Genre.ACTION)
                )
        );
         movieService = new MovieService(movieRepository);
    }

    @Test
    public void should_find_movies_by_genre() {
        Collection<Movie> byGenre = movieService.findByGenre(Genre.ACTION);
        assertThat (getMoviesId(byGenre), is( Arrays.asList(1,7) ));
    }

    @Test
    public void should_find_movies_by_duration() {
        Collection<Movie> byDuration = movieService.findByDuration(120);
        assertThat(getMoviesId(byDuration), is(Arrays.asList(1,7)));
    }

    private List<Integer> getMoviesId(Collection<Movie> byDuration) {
        return byDuration.stream().map(movie -> movie.getId()).collect(Collectors.toList());
    }

}