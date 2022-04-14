package com.example.enstruct.repository;

import com.example.enstruct.model.Announcement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {

    @Query(value = "select * from announcements where course_code = ?1 order by date ASC", nativeQuery = true)
    List<Announcement> getCourseAnnouncements(String course_code);

    @Query(value = "select * from announcements order by date ASC", nativeQuery = true)
    List<Announcement> getAnnouncementsAsc();
}
