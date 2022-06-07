package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.FileDao;
import com.diplom.alex.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FileDAOImpl implements FileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createFile(FileModel file) {
        String sqlToInsert = "INSERT INTO files (content_type, content) VALUES(?,?)";
        jdbcTemplate.update(sqlToInsert, file.getContentType(), file.getContent());
    }

    @Override
    public FileModel getFileById(int id) {
        String sqlToSelect = "SELECT * FROM files WHERE id = ?";
        List<FileModel> files = jdbcTemplate.query(sqlToSelect, new Object[]{id},
                new BeanPropertyRowMapper<>(FileModel.class));
        return files.isEmpty() ? null : files.get(0);
    }
}
