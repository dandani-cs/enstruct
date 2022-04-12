package com.example.enstruct.p_repository;

import com.example.enstruct.p_model.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends CrudRepository<Classes, String>{
}