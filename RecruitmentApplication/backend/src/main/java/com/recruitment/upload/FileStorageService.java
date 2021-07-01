package com.recruitment.upload;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB FileDB = null;
        try {
            FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
            return fileDBRepository.save(FileDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public FileDB getFileByName(String name){
        return fileDBRepository.findFileDBByName(name);
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public List<FileDB> getFiles(){
        return fileDBRepository.findAll();
    }
}