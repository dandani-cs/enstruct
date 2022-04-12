package com.example.enstruct.service;

import com.example.enstruct.model.Announcement;
import com.example.enstruct.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    private AnnouncementRepository repository;

    @Override
    public List<Announcement> getAnnouncements() {
        return (List<Announcement>) repository.findAll();
    }

    @Override
    public Announcement getAnnouncement(Long announcementId) {
        Optional optional = repository.findById(announcementId);
        if(!optional.isPresent())
            return null;
        else
            return (Announcement) optional.get();
    }

    @Override
    public Announcement addAnnouncement(Announcement announcement) {
        LocalDate dat = LocalDate.now();
        dat.plusDays(4);
        announcement.setDate(dat);
        return repository.save(announcement);
    }

    @Override
    public Announcement updateAnnouncement(Announcement announcement) {
        return repository.save(announcement);
    }

    @Override
    public List<Announcement> getCourseAnnouncements(String course_code) {
        return repository.getCourseAnnouncements(course_code);
    }

    @Override
    public List<Announcement> getAnnouncementsAsc() {
        return repository.getAnnouncementsAsc();
    }

    @Override
    public void deleteAnnouncement(Long announcement_id) {
        repository.deleteById(announcement_id);
    }
}
