package com.example.enstruct.repository;

import com.example.enstruct.model.Attachment;
import com.example.enstruct.model.Classes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    @Query(value = "select * from attachments where filename = ?1 && user_id = ?2 limit 0,1", nativeQuery = true)
    public Optional<Attachment> getInsertedAttachment(String filename, Long userId);
}
