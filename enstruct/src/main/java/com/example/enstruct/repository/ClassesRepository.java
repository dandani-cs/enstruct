package com.example.enstruct.repository;

import com.example.enstruct.model.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends CrudRepository<Classes, String>{
}