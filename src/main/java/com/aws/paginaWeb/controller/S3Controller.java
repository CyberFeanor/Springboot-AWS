package com.aws.paginaWeb.controller;

import com.aws.paginaWeb.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file){
            s3Service.uploadFileS3(file);
            String response = "El archivo " + file.getOriginalFilename() + " fue subido correctamente al bucket S3";
            return new ResponseEntity<String>(response, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        return new ResponseEntity<List<String>>(s3Service.getFilesS3(), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("key") String param){
        InputStreamResource resource = new InputStreamResource(s3Service.downloadFileS3(param));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"").body(resource);
    }
}
