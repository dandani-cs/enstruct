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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

            User teacher = e.get(i).getCourseCode().getTeacherId();

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
}
