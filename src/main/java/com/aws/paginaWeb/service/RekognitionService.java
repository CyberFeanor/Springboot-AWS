package com.aws.paginaWeb.service;

import com.amazonaws.services.rekognition.model.DetectModerationLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface RekognitionService {

    DetectModerationLabelsResult compareImage(MultipartFile imageToCheck) throws IOException;
}
