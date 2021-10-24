package com.api.smart_city.repository;

import com.api.smart_city.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
