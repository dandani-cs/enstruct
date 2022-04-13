package com.example.enstruct.repository;

import com.example.enstruct.model.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
}
