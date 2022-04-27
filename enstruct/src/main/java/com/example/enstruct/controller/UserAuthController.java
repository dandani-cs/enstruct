package com.example.enstruct.controller;


import com.example.enstruct.model.Assignment;
import com.example.enstruct.model.Classes;
import com.example.enstruct.model.User;
import com.example.enstruct.service.IAssignmentService;
import com.example.enstruct.service.IClassesService;
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
public class UserAuthController {
    @Autowired
    private IUserService service;

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showStudentDashboard(Model model)
    {
        boolean is_not_logged_in = AuthManager.getInstance().getLoggedInUser() == null;
        boolean has_invalid_priv = AuthManager.getInstance().getLoggedInUser().getTeacher();

        if(is_not_logged_in || has_invalid_priv)
            return "redirect:/login";

        return "studentDashboard";
    }

    @RequestMapping(value = "/student/profile", method = RequestMethod.GET)
    public ModelAndView showStudentProfile(ModelMap model)
    {
        User curr_logged_in = AuthManager.getInstance().getLoggedInUser();

        if(curr_logged_in == null)
        {
            return new ModelAndView("redirect:/login", model);
        }
        List<Enrollment> courses_enrolled = enrollmentService.findByUserId(curr_logged_in.getUserId());

        System.out.println("COURSES ENROLLED!");
        for(Enrollment e : courses_enrolled) {
            System.out.println(e.getCourseCode().getCourseName());
        }

        model.addAttribute("enrollments", courses_enrolled);
        model.addAttribute("user", curr_logged_in);

        return new ModelAndView("studentProfile", model);
    }

    @RequestMapping(value = "/instructor", method = RequestMethod.GET)
    public String showInstructorDashboard(Model model)
    {
        boolean is_not_logged_in = AuthManager.getInstance().getLoggedInUser() == null;


        if(is_not_logged_in) {
            return "redirect:/login";
        } else {
            boolean has_invalid_priv = !AuthManager.getInstance().getLoggedInUser().getTeacher();
            if (has_invalid_priv)
                return "redirect:/login";
        }

        List<Assignment> agenda = assignmentService.getAllAssignments();
        List<String> courses = new ArrayList<>();
        for (Assignment a : agenda) {
            courses.add(a.getCourse().getCourseName());
        }

        model.addAttribute("agenda", agenda);
        model.addAttribute("courses", courses);

        return "instructorHome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model, @RequestParam(required = false) String error)
    {
        if(error != null && !error.isEmpty())
            model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ModelAndView authenticateUser(@ModelAttribute User user, ModelMap model)
    {
        User existing_entry = service.findByUsername(user.getUserName());
        // TODO: ?
        if(existing_entry == null)
        {
            model.addAttribute("error", "nonexistent_user");
            return new ModelAndView("redirect:/login", model);
        }
        if(!existing_entry.getPassword().equals(user.getPassword()))
        {
            model.addAttribute("error", "bad_password");
            return new ModelAndView("redirect:/login", model);
        }
        AuthManager.getInstance().loginUser(existing_entry);
        String dashboard_link = String.format("redirect:%s", existing_entry.getTeacher() ? "/instructor" : "/student");
        return new ModelAndView(dashboard_link, model);
    }
}
