package com.example.enstruct.service;

import com.example.enstruct.model.Classes;
import com.example.enstruct.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassesService implements IClassesService{

    @Autowired
    private ClassesRepository repository;
    @Override
    public List<Classes> getClasses() {
        return (List<Classes>) repository.findAll();
    }

    @Override
    public Classes getClass(String course_code) {
        Optional optional = repository.findById(course_code);
        if(!optional.isPresent())
            return null;
        else
            return (Classes) optional.get();
    }

    @Override
    public Classes addClass(Classes clas) {
        return repository.save(clas);
    }

    @Override
    public Classes updateClass(Classes clas) {
        return repository.save(clas);
    }

    @Override
    public void deleteClass(String course_code) {
        repository.deleteById(course_code);
    }
}
