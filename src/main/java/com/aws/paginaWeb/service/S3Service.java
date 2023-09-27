package com.aws.paginaWeb.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


@Service
public interface S3Service {


    void uploadFileS3(MultipartFile file);

    List<String> getFilesS3();

    InputStream downloadFileS3(String key);


}
