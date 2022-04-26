package com.example.enstruct.service;

import com.example.enstruct.model.Attachment;
import com.example.enstruct.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService implements IAttachmentService {

    @Autowired
    private AttachmentRepository repository;
    @Override
    public List<Attachment> getAttachments() {
        return (List<Attachment>) repository.findAll();
    }

    @Override
    public Attachment getAttachment(Long attachment_id) {
        Optional optional = repository.findById(attachment_id);
        if(!optional.isPresent())
            return null;
        else
            return (Attachment) optional.get();
    }

    @Override
    public Attachment addAttachment(Attachment attachment) {
        return repository.save(attachment);
    }

    @Override
    public Attachment updateAttachment(Attachment attachment) {
        return repository.save(attachment);
    }

    @Override
    public void deleteAttachment(Long attachment_id) {
        repository.deleteById(attachment_id);
    }

    @Override
    public Attachment getInsertedAttachment(String filename, Long userId) {
        Optional optional = repository.getInsertedAttachment(filename, userId);
        if(!optional.isPresent())
            return null;
        else
            return (Attachment) optional.get();
    }
}
