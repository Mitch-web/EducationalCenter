package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.PostDAO;
import com.diplom.alex.model.PostModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.diplom.alex.constants.ApplicationConstants.*;

public class PostDAOImpl implements PostDAO {

    private final JdbcTemplate jdbcTemplate;

    public PostDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<PostModel> getPostById(int id) {
        return Optional.ofNullable(extractPost(String.format("SELECT * FROM %s WHERE id=?", POST_TABLE),
                new Object[]{id}));
    }

    @Override
    public List<PostModel> getPostsByTitle(String title) {
        return jdbcTemplate.query(String.format("SELECT * FROM %s", POST_TABLE),
                new BeanPropertyRowMapper<>(PostModel.class));
    }

    @Override
    public List<PostModel> getPosts() {
        return jdbcTemplate.query(String.format("SELECT * FROM %s", POST_TABLE),
                new BeanPropertyRowMapper<>(PostModel.class));
    }

    @Override
    public List<PostModel> getPostsByCourse(String courseName) {
        return jdbcTemplate.query(String.format("SELECT p.id, p.title, p.subtitle FROM %s AS cp " +
                "JOIN %s AS c ON cp.course_id = c.id " +
                "JOIN %s AS p ON cp.post_id = p.id where c.name = '%s'",
                        COURSE_HAVE_POSTS_TABLE, COURSE_TABLE, POST_TABLE, courseName),
                new BeanPropertyRowMapper<>(PostModel.class));
    }

    @Override
    public List<PostModel> getPostsByDeadline(String deadline) {
        return jdbcTemplate.query(String.format("SELECT * FROM %s where deadline LIKE '%s'", POST_TABLE, deadline),
                new BeanPropertyRowMapper<>(PostModel.class));
    }

    @Override
    public List<PostModel> getPostsByDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE DATE(deadline) >= DATE('%s')", POST_TABLE, currentDate),
                new BeanPropertyRowMapper<>(PostModel.class));
    }

    @Override
    public boolean addNewPost(PostModel post, int courseId) {
        final String INSERT_POST_SQL = "INSERT INTO " + POST_TABLE + " (title, subtitle, deadline) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(INSERT_POST_SQL, new String[] {"id"});
                    ps.setString(1, post.getTitle());
                    ps.setString(2, post.getSubtitle());
                    ps.setString(3, post.getDeadline());
                    return ps;
                },
                keyHolder);

        jdbcTemplate.update("INSERT INTO " + COURSE_HAVE_POSTS_TABLE + " VALUES(?, ?)", courseId, keyHolder.getKey());
        return true;
    }



    private PostModel extractPost(String query, Object[] params) {
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PostModel.class), params)
                .stream()
                .findAny()
                .orElse(null);
    }
}
