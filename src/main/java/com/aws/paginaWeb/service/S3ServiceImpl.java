package com.aws.paginaWeb.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3ServiceImpl implements S3Service{
private static final Logger LOGGER = LoggerFactory.getLogger(S3ServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket_name}")
    private String bucketName;


    @Override
    public void uploadFileS3(MultipartFile file) {
        File mainFile = new File(file.getOriginalFilename());

        try(FileOutputStream stream = new FileOutputStream(mainFile)){
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            LOGGER.info("Subiendo archivo con el nombre " + newFileName);
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
            amazonS3.putObject(request);

        } catch (Exception e){
            LOGGER.error(e.getMessage(),e);

        }
    }

    @Override
    public List<String> getFilesS3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        //Convertir el result en lista de objetos s3
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map( item -> {
            return item.getKey();
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public InputStream downloadFileS3(String key) {
        S3Object object = amazonS3.getObject(bucketName, key);
        return object.getObjectContent();
    }



}
