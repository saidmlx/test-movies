package com.saidmorales.movies.data;

import com.saidmorales.movies.model.Genre;
import com.saidmorales.movies.model.Movie;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryImpl movieRepository;

    @Before
    public void setUp() throws Exception {
        DataSource dataSource =
                new DriverManagerDataSource("jdbc:h2:~/test", "sa", "");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql") );

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new MovieRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void should_get_all_movies() throws SQLException {

        Collection<Movie> allMovies = movieRepository.findAll();

        assertThat(allMovies, CoreMatchers.is( Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento",113, Genre.THRILLER),
                new Movie(3, "There's something About Mary", 119, Genre.COMEDY),
                new Movie(4, "Super 8",112 , Genre.THRILLER),
                new Movie(5, "Scream", 11, Genre.HORROR),
                new Movie(6, "Home alone", 103, Genre.COMEDY),
                new Movie(7, "Super man", 136, Genre.ACTION)
        )));
    }

    @Test
    public void should_get_movie_by_id() {
        Movie movie = movieRepository.findById(2);
        assertThat(movie,CoreMatchers.is(new Movie(2, "Memento",113, Genre.THRILLER)));
    }
    @Test
    public void should_get_movie_by_name() {
        Collection<Movie> allMovies = movieRepository.findByName("%super%");
        assertThat(allMovies,CoreMatchers.is( Arrays.asList(
                new Movie(4, "Super 8",112 , Genre.THRILLER),
                new Movie(7, "Super man", 136, Genre.ACTION)
        )));
    }

    @Test
    public void should_insert_movie() {
        movieRepository.SaveOrUpdate(new Movie("Fast and Fourios", 136, Genre.ACTION));
        Movie movie = movieRepository.findById(8);
        assertThat(movie,CoreMatchers.is(new Movie(8, "Fast and Fourios", 136, Genre.ACTION)));
    }
}