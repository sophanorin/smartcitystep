package com.api.smart_city.controller;

import com.api.smart_city.model.Role;
import com.api.smart_city.service.CloudinaryService;
import com.api.smart_city.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttachmentController {
    private final CloudinaryService cloudinaryService;

    @PostMapping("/attachments/upload")
    public ResponseEntity upload(@RequestBody Collection<MultipartFile> files) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/attachments/upload").toUriString());
        return ResponseEntity.created(uri).body(cloudinaryService.uploadFile(files));
    }
}
