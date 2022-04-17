package com.example.enstruct.controller;

import com.example.enstruct.model.User;
import com.example.enstruct.service.IUserService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UserAuthController {
    @Autowired
    private IUserService service;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showStudentDashboard(Model model)
    {
        boolean is_not_logged_in = AuthManager.getInstance().getLoggedInUser() == null;
        boolean has_invalid_priv = AuthManager.getInstance().getLoggedInUser().getTeacher();

        if(is_not_logged_in || has_invalid_priv)
            return "redirect:/login";

        return "studentDashboard";
    }

    @RequestMapping(value = "/instructor", method = RequestMethod.GET)
    public String showInstructorDashboard(Model model)
    {
        boolean is_not_logged_in = AuthManager.getInstance().getLoggedInUser() == null;
        boolean has_invalid_priv = !AuthManager.getInstance().getLoggedInUser().getTeacher();

        if(is_not_logged_in || has_invalid_priv)
            return "redirect:/login";

        return "instructorDashboard";
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
        // TODO:
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