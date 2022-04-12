package com.example.enstruct.p_repository;

import com.example.enstruct.p_model.Attachments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentsRepository extends CrudRepository<Attachments, Long> {
}
