package com.recruitment.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.recruitment.UrlMapping.APPLICATIONS;

@Controller
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping(APPLICATIONS + "/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return "redirect:/";
    }

    @GetMapping(APPLICATIONS + "/files/{name}")
    public ResponseEntity<byte[]> getFile(@PathVariable String name) {
        //FileDB fileDB = storageService.getFile(id);
        FileDB fileDB = storageService.getFileByName(name);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
