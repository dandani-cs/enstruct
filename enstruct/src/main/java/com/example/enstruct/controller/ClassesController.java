package com.example.enstruct.controller;

import com.example.enstruct.model.Classes;
import com.example.enstruct.model.Enrollment;
import com.example.enstruct.model.User;
import com.example.enstruct.service.IClassesService;
import com.example.enstruct.service.IEnrollmentService;
import com.example.enstruct.service.IUserService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ClassesController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/student/classes", method = RequestMethod.GET)
    public String showStudentClasses(Model model)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Enrollment> e = enrollmentService.findByUserId(user.getUserId());
        System.out.println("ESIZE: "+ e.size());

        List<Classes> c = new ArrayList<>();
        List<String> courseCode = new ArrayList<>();
        List<String> section = new ArrayList<>();
        List<String> professor = new ArrayList<>();

        for(int i = 0; i < e.size(); i++) {
            c.add(e.get(i).getCourseCode());

            String cc = e.get(i).getCourseCode().getCourseCode();

            int courseCodeIndex = cc.indexOf("_");
            int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

            courseCode.add(cc.substring(0, courseCodeIndex));
            section.add(cc.substring(courseCodeIndex+1, sectionIndex));

//            User teacher = e.get(i).getCourseCode().getTeacherId();
//
//            String teacherName = null;
//            if(teacher.getFirstName() != null) {
//                teacherName = teacher.getFirstName();
//            }
//            if(teacher.getMiddleName() != null) {
//                teacherName += " " + teacher.getMiddleName();
//            }
//            if(teacher.getLastName() != null) {
//                teacherName += " " + teacher.getLastName();
//            }
//
//            professor.add(teacherName);
        }

        model.addAttribute("classes", c);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
//        model.addAttribute("professor", professor);

        return "studentClasses";
    }

    @RequestMapping(value = "/instructor/classes", method = RequestMethod.GET)
    public String showInstructorClasses(Model model)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Classes> c = classesService.getClassesByTeacherId(user.getUserId());
        System.out.println("CSIZE: "+ c.size());

        List<String> courseCode = new ArrayList<>();
        List<String> section = new ArrayList<>();
        List<String> professor = new ArrayList<>();

        for(int i = 0; i < c.size(); i++) {

            String cc = c.get(i).getCourseCode();

            int courseCodeIndex = cc.indexOf("_");
            int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

            courseCode.add(cc.substring(0, courseCodeIndex));
            section.add(cc.substring(courseCodeIndex+1, sectionIndex));

            User teacher = c.get(i).getTeacherId();

            String teacherName = null;
            if(teacher.getFirstName() != null) {
                teacherName = teacher.getFirstName();
            }
            if(teacher.getMiddleName() != null) {
                teacherName += " " + teacher.getMiddleName();
            }
            if(teacher.getLastName() != null) {
                teacherName += " " + teacher.getLastName();
            }

            professor.add(teacherName);
        }

        model.addAttribute("classes", c);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        model.addAttribute("professor", professor);

        return "instructorClasses";
    }

    @RequestMapping(value = "/instructor/classes/addClass", method = RequestMethod.GET)
    public String addClass(Model model)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Classes> c = classesService.getClasses();
        List<User> u = userService.findAllStudents();


        model.addAttribute("classes", c);
        model.addAttribute("students", u);

        return "instructorAddClass";
    }

    @RequestMapping(value = "/instructor/classes/addClass/{courseCode}/{courseName}/{enabled}/{students}", method = RequestMethod.POST)
    public ModelAndView addClass(Model model, @PathVariable String courseCode, @PathVariable String courseName, @PathVariable Boolean enabled, @PathVariable String students)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        Classes c = new Classes();
        c.setCourseCode(courseCode);
        c.setCourseName(courseName);
        c.setEnabled(enabled);
        c.setTeacherId(user);

        classesService.addClass(c);

        System.out.println("STUDENTS: " + students);

        List<Long> enrollees = new ArrayList<>();

        if(!students.equals("-1")) {
            try{
                while(true) {
                    enrollees.add(Long.valueOf(students.substring(0, students.indexOf("."))));
                    students = students.substring(students.indexOf(".")+1);
                }
            } catch(Exception e) {}
        }

        for(int i = 0; i < enrollees.size(); i++) {
            Enrollment e = new Enrollment();
            e.setCourseCode(c);
            e.setUser(userService.getUser(enrollees.get(i)));
            enrollmentService.addEnrollment(e);
        }

        return new ModelAndView("redirect:/instructor/classes", (Map<String, ?>) model);
    }
}
