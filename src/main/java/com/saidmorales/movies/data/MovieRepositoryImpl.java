package com.saidmorales.movies.data;

import com.saidmorales.movies.model.Genre;
import com.saidmorales.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MovieRepositoryImpl implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Movie> findAll() {
        return this.jdbcTemplate.query ("select * from movies",movieRowMapper);
    }

    @Override
    public void SaveOrUpdate(Movie movie) {

    }

    private static RowMapper<Movie>  movieRowMapper = (rs, rowNum) -> new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("duration"),
            Genre.valueOf(rs.getString("genre"))
    );


}
