package com.aws.paginaWeb.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;

@Service
public class RekognitionServiceImpl implements RekognitionService{

    @Autowired
    private AmazonRekognition rekognition;

    @Override
    public DetectModerationLabelsResult compareImage(MultipartFile imageToCheck) throws IOException {

        DetectModerationLabelsRequest request = new DetectModerationLabelsRequest()
                .withImage(new Image().withBytes(ByteBuffer.wrap(imageToCheck.getBytes())));

        return rekognition.detectModerationLabels(request);
    }
}
