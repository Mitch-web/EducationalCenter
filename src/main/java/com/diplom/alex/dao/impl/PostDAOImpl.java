package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.PostDAO;
import com.diplom.alex.model.FileModel;
import com.diplom.alex.model.PostModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.diplom.alex.constants.ApplicationConstants.*;

public class PostDAOImpl implements PostDAO {

    private static final String ID = "id";
    private static final String INSERT_POST_SQL = "INSERT INTO " + POST_TABLE + " (title, subtitle, deadline) VALUES (?, ?, ?)";
    private static final String INSERT_FILE = "INSERT INTO " + FILES_TABLE + " (content_type, content) VALUES (?,?)";

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
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String currentDate = sdf.format(new Date());
        BeanPropertyRowMapper<PostModel> rowMapper = new BeanPropertyRowMapper<>(PostModel.class);
        rowMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE DATE(deadline) >= DATE('%s')", POST_TABLE, currentDate),
                rowMapper);
    }

    @Override
    public boolean addNewPost(PostModel post, int courseId, FileModel file) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_POST_SQL, new String[] {ID});
                    populatePostStatement(ps, post);
                    return ps;
                }, keyHolder);

        if (null != file) {
            insertFile(file, keyHolder);
        }

        jdbcTemplate.update("INSERT INTO " + COURSE_HAVE_POSTS_TABLE + " VALUES(?, ?)", courseId, keyHolder.getKey());
        return true;
    }

    private void populatePostStatement(PreparedStatement ps, PostModel post) throws SQLException {
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getSubtitle());
        ps.setString(3, post.getDeadline());
    }

    private void insertFile(FileModel file, KeyHolder keyHolder) {
        KeyHolder fileKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_FILE, new String[]{ID});
            populateFileStatement(ps, file);
            return ps;
        }, fileKeyHolder);
        jdbcTemplate.update("UPDATE " + POST_TABLE + " SET file_id=" +
                fileKeyHolder.getKey() + " WHERE id=" + keyHolder.getKey());
    }

    private void populateFileStatement(PreparedStatement ps, FileModel file) throws SQLException {
        ps.setString(1, file.getContentType());
        ps.setBytes(2, file.getContent());
    }

    private PostModel extractPost(String query, Object[] params) {
        BeanPropertyRowMapper<PostModel> rowMapper = new BeanPropertyRowMapper<>(PostModel.class);
        rowMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query(query, rowMapper, params)
                .stream()
                .findAny()
                .orElse(null);
    }
}
