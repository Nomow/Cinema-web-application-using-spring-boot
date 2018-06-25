package me.kursaDarbs.app.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
    public void store(MultipartFile file, String folder, String fileName);
    public Resource loadFile(String filename);
    public void deleteAll();
    public void init();
    public Stream<Path> loadFiles();
    public void delete(String folder, String fileName);
    public void update(String folder, String fileName, String newFileName);
    }