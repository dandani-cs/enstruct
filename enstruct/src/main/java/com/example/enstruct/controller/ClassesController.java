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
    private IEnrollmentService EnrollmentService;

    @Autowired
    private IClassesService ClassesService;

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public String showClasses(Model model)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Enrollment> e = EnrollmentService.findByUserId(user.getUserId());
        System.out.println("ESIZE: "+ e.size());
        List<Classes> c = new ArrayList<>();

        for(int i = 0; i < e.size(); i++) {
            c.add(e.get(i).getCourseCode());
            System.out.println("C: " + c.get(i).getCourseName());
        }

        model.addAttribute("classes", c);

        return "classes";
    }
}
