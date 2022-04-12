package com.example.enstruct.p_testcontroller;

import com.example.enstruct.p_model.Attachments;
import com.example.enstruct.p_service.IAttachmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttachmentsController {

    @Autowired
    private IAttachmentsService service;

    @RequestMapping(value = "/getAttachments")
    public List<Attachments> getAttachments() {
        System.out.println("halo");
        return service.getAttachments();
    }

    @RequestMapping(value = "/getAttachment/{attachment_id}")
    public Attachments getAttachment(@PathVariable Long attachment_id) {
        return service.getAttachment(attachment_id);
    }

    @RequestMapping(value = "/addAttachment", method = RequestMethod.POST)
    public Attachments addAttachment(@RequestBody Attachments attachment) {
        return service.addAttachment(attachment);
    }

    @RequestMapping(value = "/updateAttachment/{attachment_id}", method = RequestMethod.POST)
    public Attachments updateAttachment(@PathVariable Long attachment_id,  @RequestBody Attachments attachment) {
        return service.updateAttachment(attachment);
    }

    @RequestMapping(value = "/deleteAttachment/{attachment_id}", method = RequestMethod.DELETE)
    public void deleteAttachment(@PathVariable Long attachment_id) {
        service.deleteAttachment(attachment_id);
    }
}
