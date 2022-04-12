package com.example.enstruct.p_repository;

import com.example.enstruct.p_model.Announcements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementsRepository extends CrudRepository<Announcements, Long> {

    @Query(value = "select * from announcements where course_code = ?1 order by date ASC", nativeQuery = true)
    List<Announcements> getCourseAnnouncements(String course_code);

}
