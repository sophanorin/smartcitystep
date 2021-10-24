package com.api.smart_city.service;

import com.api.smart_city.dto.post.AttachmentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface CloudinaryService {
    public Collection<AttachmentDTO> uploadFile(Collection<MultipartFile> files);
    File convertMultiPartToFile(MultipartFile file) throws IOException;
}
