package com.example.enstruct.p_service;

import com.example.enstruct.p_model.Attachments;

import java.util.List;

public interface IAttachmentsService {

    List<Attachments> getAttachments();
    Attachments getAttachment(Long attachment_id);
    Attachments addAttachment(Attachments attachment);
    Attachments updateAttachment(Attachments attachment);
    void deleteAttachment(Long attachment_id);
}
