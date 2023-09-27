package com.aws.paginaWeb.controller;


import com.aws.paginaWeb.service.RekognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/rekognition")
public class RekognitionController {

    @Autowired
    private RekognitionService rekognitionService;

    @PostMapping("/compare-images")
    public Object detectModerationLabels(@RequestParam("imageToCheck") MultipartFile imageToCheck) throws IOException {
        return ResponseEntity.ok(rekognitionService.compareImage(imageToCheck));
    }
}
