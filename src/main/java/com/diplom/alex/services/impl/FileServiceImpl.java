package com.diplom.alex.services.impl;

import com.diplom.alex.dao.FileDao;
import com.diplom.alex.model.FileModel;
import com.diplom.alex.services.FileService;

import java.util.Base64;

public class FileServiceImpl implements FileService {

    private final FileDao fileDao;

    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    public void createFile(FileModel file) {
        file.setContent(Base64.getEncoder().encode(file.getContent()));
        fileDao.createFile(file);
    }

    @Override
    public FileModel getFileById(int id) {
        return fileDao.getFileById(id);
    }
}
