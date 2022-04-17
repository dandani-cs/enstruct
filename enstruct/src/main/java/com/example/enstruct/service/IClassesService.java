package com.example.enstruct.service;

import com.example.enstruct.model.Classes;

import java.util.List;

public interface IClassesService {
    List<Classes> getClasses();
    Classes getClass(String class_id);
    List<Classes> getClassesByTeacherId(Long teacherId);
    Classes addClass(Classes clas);
    Classes updateClass(Classes clas);
    void deleteClass(String class_id);
}
