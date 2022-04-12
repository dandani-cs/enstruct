package com.example.enstruct.p_service;

import com.example.enstruct.p_model.Attachments;
import com.example.enstruct.p_repository.AttachmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentsService implements IAttachmentsService{

    @Autowired
    private AttachmentsRepository repository;
    @Override
    public List<Attachments> getAttachments() {
        return (List<Attachments>) repository.findAll();
    }

    @Override
    public Attachments getAttachment(Long attachment_id) {
        Optional optional = repository.findById(attachment_id);
        if(!optional.isPresent())
            return null;
        else
            return (Attachments) optional.get();
    }

    @Override
    public Attachments addAttachment(Attachments attachment) {
        return repository.save(attachment);
    }

    @Override
    public Attachments updateAttachment(Attachments attachment) {
        return repository.save(attachment);
    }

    @Override
    public void deleteAttachment(Long attachment_id) {
        repository.deleteById(attachment_id);
    }
}
