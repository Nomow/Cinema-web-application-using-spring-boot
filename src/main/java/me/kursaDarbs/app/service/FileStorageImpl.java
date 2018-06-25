package me.kursaDarbs.app.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import me.kursaDarbs.app.service.FileStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageImpl implements FileStorage {


    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("src/main/resources/static/img/");
    @Override
    public void store(MultipartFile file, String folder, String fileName){
        Path temp =  Paths.get(rootLocation.toString(), folder);
        fileName += ".jpg";
        System.out.println(temp.resolve(fileName));
        System.out.println(file.getOriginalFilename());

        try {
            Files.copy(file.getInputStream(), temp.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

//    @Override
//    public void store(MultipartFile file, String folder, String fileName){
//        Path temp =  Paths.get(rootLocation.toString(), folder);
//
//
//        try {
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
//        } catch (Exception e) {
//            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
//        }
//    }


    @Override
    public void update(String folder, String fileName, String newFileName) {
        Path path =  Paths.get(rootLocation.toString(), folder);
        fileName += ".jpg";
        newFileName += ".jpg";
        System.out.println("old newFileName: " + fileName);
        System.out.println("new newFileName: " + newFileName);

        Path oldFullPath = path.resolve(fileName);
        Path newFullPath = path.resolve(newFileName);
        System.out.println("old path: " + oldFullPath);
        System.out.println("new path: " + newFullPath);

        File oldFile = new File(oldFullPath.toString());
        File newFile = new File(newFullPath.toString());
        if(oldFile.renameTo(newFile)){
            System.out.println("Rename succesful");
        }else{
            System.out.println("Rename failed");
        }





    }


    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }
    }

    @Override
    public void delete(String folder, String fileName) {
        Path temp =  Paths.get(rootLocation.toString(), folder);
        fileName += ".jpg";
        try {
            Files.delete(temp.resolve(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    @Override
    public Stream<Path> loadFiles() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new RuntimeException("\"Failed to read stored file");
        }
    }
}