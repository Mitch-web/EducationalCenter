package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.HomeworkDAO;
import com.diplom.alex.model.FileModel;
import com.diplom.alex.model.HomeworkModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.diplom.alex.constants.ApplicationConstants.*;

public class HomeworkDAOImpl implements HomeworkDAO {

    private static final String ID = "id";
    private static final String INSERT_HOMEWORK = "INSERT INTO " + HOMEWORKS_TABLE + " (content_type, content) VALUES (?,?)";


    private JdbcTemplate jdbcTemplate;

    public HomeworkDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<HomeworkModel> getHomeworksByUser(int userId) {
        return jdbcTemplate.query("SELECT h.id, h.content_type, h.content FROM " + HOMEWORKS_TABLE +
                " AS h JOIN users_have_posts AS uhp ON h.id=uhp.homework_id AND uhp.user_id=?",
                new BeanPropertyRowMapper<>(HomeworkModel.class), userId);
    }

    @Override
    public HomeworkModel getHomeworkByPostAndUser(int postId, int userId) {
        List<HomeworkModel> homeworks = jdbcTemplate.query("SELECT h.id, h.content_type, h.content FROM "
                        + HOMEWORKS_TABLE + " AS h JOIN " + USERS_HAVE_POSTS_TABLE +
                        " AS uhp ON h.id=uhp.homework_id AND uhp.user_id=? AND uhp.post_id=?",
                new BeanPropertyRowMapper<>(HomeworkModel.class), userId, postId);
        return homeworks.isEmpty() ? null : homeworks.get(0);
    }

    @Override
    public void createHomeworkToPost(HomeworkModel homework, int postId, int userId) {
        KeyHolder fileKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_HOMEWORK, new String[]{ID});
            populateHomeworkStatement(ps, homework);
            return ps;
        }, fileKeyHolder);

       jdbcTemplate.update("INSERT INTO " + USERS_HAVE_POSTS_TABLE + " VALUES(?,?,?)",
               userId, postId, fileKeyHolder.getKey());
    }

    private void populateHomeworkStatement(PreparedStatement ps, HomeworkModel file) throws SQLException {
        ps.setString(1, file.getContentType());
        ps.setBytes(2, file.getContent());
    }
}
