package com.example.enstruct.service;

import com.example.enstruct.model.Attachment;

import java.util.List;
import java.util.Optional;

public interface IAttachmentService {

    List<Attachment> getAttachments();
    Attachment getAttachment(Long attachmentId);
    Attachment addAttachment(Attachment attachment);
    Attachment updateAttachment(Attachment attachment);
    void deleteAttachment(Long attachmentId);

    Attachment getInsertedAttachment(String filename, Long userId);
}
