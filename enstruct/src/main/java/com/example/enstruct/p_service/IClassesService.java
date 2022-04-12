package com.example.enstruct.p_service;

import com.example.enstruct.p_model.Classes;

import java.util.List;

public interface IClassesService {
    List<Classes> getClasses();
    Classes getClass(String class_id);
    Classes addClass(Classes clas);
    Classes updateClass(Classes clas);
    void deleteClass(String class_id);
}
