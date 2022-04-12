package com.example.enstruct.p_service;

import com.example.enstruct.p_model.Announcements;

import java.util.List;

public interface IAnnouncementsService {

    List<Announcements> getAnnouncements();
    Announcements getAnnouncement(Long announcement_id);
    Announcements addAnnouncement(Announcements announcement);
    Announcements updateAnnouncement(Announcements announcement);
    List<Announcements> getCourseAnnouncements(String course_code);
    void deleteAnnouncement(Long announcement_id);
}
