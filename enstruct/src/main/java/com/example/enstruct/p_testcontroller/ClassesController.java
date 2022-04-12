package com.example.enstruct.p_testcontroller;

import com.example.enstruct.p_model.Classes;
import com.example.enstruct.p_service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassesController {

    @Autowired
    private IClassesService service;

    @RequestMapping(value = "/getClasses")
    public List<Classes> getClasses() {
        return service.getClasses();
    }

    @RequestMapping(value = "/getClass/{course_code}")
    public Classes getClass(@PathVariable String course_code) {
        return service.getClass(course_code);
    }

    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public Classes addClass(@RequestBody Classes clas) {
        return service.addClass(clas);
    }

    @RequestMapping(value = "/updateClass/{course_code}", method = RequestMethod.POST)
    public Classes updateClass(@PathVariable String course_code,  @RequestBody Classes clas) {
        return service.updateClass(clas);
    }

    @RequestMapping(value = "/deleteClass/{course_code}", method = RequestMethod.DELETE)
    public void deleteClass(@PathVariable String course_code) {
        service.deleteClass(course_code);
    }
}
