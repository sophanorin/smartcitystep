package com.api.smart_city.service;
import com.api.smart_city.dto.post.AttachmentDTO;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CloudinaryServiceImplement implements CloudinaryService{

    private final Cloudinary cloudinaryConfig;

    @Override
    public Collection<AttachmentDTO> uploadFile(Collection<MultipartFile> files) {
        if(files.size() < 0)
            throw new IllegalStateException("files body could not empty");

       Collection<AttachmentDTO> attachments = new ArrayList<>();
       files.forEach(file-> {
           try {
               File uploadedFile = convertMultiPartToFile(file);
               Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());

               attachments.add(new AttachmentDTO(
                       (long) (Math.random() * 100),
                       uploadResult.get("original_filename").toString(),
                       uploadResult.get("secure_url").toString())
               );

           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       });

       return attachments;
    }

    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String[] __file = filename.split("\\.");
        File convFile = File.createTempFile(__file[0],__file[__file.length - 1]);

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
