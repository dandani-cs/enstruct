package com.example.enstruct.p_service;

import com.example.enstruct.p_repository.AnnouncementsRepository;
import com.example.enstruct.p_model.Announcements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementsService implements IAnnouncementsService{

    @Autowired
    private AnnouncementsRepository repository;

    @Override
    public List<Announcements> getAnnouncements() {
        return (List<Announcements>) repository.findAll();
    }

    @Override
    public Announcements getAnnouncement(Long announcement_id) {
        Optional optional = repository.findById(announcement_id);
        if(!optional.isPresent())
            return null;
        else
            return (Announcements) optional.get();
    }

    @Override
    public Announcements addAnnouncement(Announcements announcement) {
        LocalDate dat = LocalDate.now();
        dat.plusDays(4);
        announcement.setDate(dat);
        return repository.save(announcement);
    }

    @Override
    public Announcements updateAnnouncement(Announcements announcement) {
        return repository.save(announcement);
    }

    @Override
    public List<Announcements> getCourseAnnouncements(String course_code) {
        return repository.getCourseAnnouncements(course_code);
    }

    @Override
    public void deleteAnnouncement(Long announcement_id) {
        repository.deleteById(announcement_id);
    }
}
