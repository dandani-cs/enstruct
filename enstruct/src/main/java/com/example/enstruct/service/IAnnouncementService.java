package com.example.enstruct.service;

import com.example.enstruct.model.Announcement;

import java.util.List;

public interface IAnnouncementService {

    List<Announcement> getAnnouncements();
    Announcement getAnnouncement(Long announcement_id);
    Announcement addAnnouncement(Announcement announcement);
    Announcement updateAnnouncement(Announcement announcement);
    List<Announcement> getCourseAnnouncements(String courseCode);
    List<Announcement> getAnnouncementsAsc();
    void deleteAnnouncement(Long announcementId);
}
