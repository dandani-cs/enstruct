package com.example.enstruct.controller;

import com.example.enstruct.model.Announcement;
import com.example.enstruct.model.Classes;
import com.example.enstruct.model.User;
import com.example.enstruct.repository.ClassesRepository;
import com.example.enstruct.service.AnnouncementService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping("/instructor/announcements")
    public ModelAndView instructorShowAnnouncements()
    {
        User instructor = AuthManager.getInstance().getLoggedInUser();

        if(instructor == null || !instructor.getTeacher())
            return new ModelAndView("redirect:/login", new ModelMap());

        List<Announcement> announcements = announcementService.getAnnouncements();
        List<Classes> courses = classesRepository.getClassesByTeacherId(instructor.getUserId());

        ModelAndView mv = new ModelAndView();
        mv.addObject("announcements", announcements);
        mv.addObject("courses", courses);
        mv.setViewName("instructorAnnouncements");
        return mv;
    }

    @PostMapping("/instructor/postAnnouncement")
    public ModelAndView instructorPostAnnouncements(@ModelAttribute Announcement announcement, ModelMap model)
    {
        announcementService.addAnnouncement(announcement);
        return new ModelAndView("redirect:/instructor/announcements", model);
    }

    @GetMapping("/student/announcements")
    public ModelAndView studentShowAnnouncements()
    {
        List<Announcement> announcements = announcementService.getAnnouncements();
        ModelAndView mv = new ModelAndView();
        mv.addObject("announcements", announcements);
        mv.setViewName("studentAnnouncements");
        return mv;
    }
}
