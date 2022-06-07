package com.diplom.alex.dao;

import com.diplom.alex.model.FileModel;

public interface FileDao {
    void createFile(FileModel file);
    FileModel getFileById(int id);
}
