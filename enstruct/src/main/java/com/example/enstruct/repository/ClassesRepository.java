package com.example.enstruct.repository;

import com.example.enstruct.model.Announcement;
import com.example.enstruct.model.Classes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepository extends CrudRepository<Classes, String>{

    @Query(value = "select * from classes where user_id = ?1", nativeQuery = true)
    List<Classes> getClassesByTeacherId(Long teacherId);
}