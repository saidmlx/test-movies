package com.saidmorales.movies.data;

import com.saidmorales.movies.model.Genre;
import com.saidmorales.movies.model.Movie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return (Movie)this.jdbcTemplate.queryForObject("select * from movies where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper(Movie.class)
                );
    }

    @Override
    public Collection<Movie> findAll() {
        return this.jdbcTemplate.query ("select * from movies",movieRowMapper);
    }

    @Override
    public void SaveOrUpdate(Movie movie) {
        String strInsert = "INSERT INTO movies (name, duration, genre) values (?, ?, ?)";
        int update = this.jdbcTemplate.update(strInsert, new Object[]{movie.getName(), movie.getDuration(), movie.getGenre().toString()});
    }

    private static RowMapper<Movie>  movieRowMapper = (rs, rowNum) -> new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("duration"),
            Genre.valueOf(rs.getString("genre"))
    );

    public Collection<Movie> findByName(String name) {
        return this.jdbcTemplate.query("select * from movies where UPPER(name)  like UPPER(?) ",
                new Object[]{name},movieRowMapper
        );
    }
}
