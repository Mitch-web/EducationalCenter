package com.diplom.alex.services;

import com.diplom.alex.model.FileModel;

public interface FileService {
    void createFile(FileModel file);
    FileModel getFileById(int id);
}
