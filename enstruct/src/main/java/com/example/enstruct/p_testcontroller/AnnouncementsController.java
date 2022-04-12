package com.example.enstruct.p_testcontroller;

import com.example.enstruct.p_model.Announcements;
import com.example.enstruct.p_service.IAnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnouncementsController {

    @Autowired
    private IAnnouncementsService service;

    @RequestMapping(value = "/getAnnouncements")
    public List<Announcements> getAnnouncements() {
        return service.getAnnouncements();
    }

    @RequestMapping(value = "/getAnnouncement/{announcement_id}")
    public Announcements getAnnouncement(@PathVariable Long announcement_id) {
        return service.getAnnouncement(announcement_id);
    }

    @RequestMapping(value = "/addAnnouncement", method = RequestMethod.POST)
    public Announcements addAnnouncement(@RequestBody Announcements announcement) {
        return service.addAnnouncement(announcement);
    }

    @RequestMapping(value = "/updateAnnouncement/{announcement_id}", method = RequestMethod.POST)
    public Announcements updateAnnouncement(@PathVariable Long announcement_id,  @RequestBody Announcements announcement) {
        return service.updateAnnouncement(announcement);
    }

    @RequestMapping(value = "/getCourseAnnouncements/{course_code}", method = RequestMethod.POST)
    public List<Announcements> getCourseAnnouncements(@PathVariable String course_code) {
        return service.getCourseAnnouncements(course_code);
    }

    @RequestMapping(value = "/deleteAnnouncement/{announcement_id}", method = RequestMethod.DELETE)
    public void deleteAnnouncement(@PathVariable Long announcement_id) {
        service.deleteAnnouncement(announcement_id);
    }
}
