package com.example.enstruct.controller;

import com.example.enstruct.model.Assignment;
import com.example.enstruct.model.Course;
import com.example.enstruct.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssignmentController {
    @Autowired
    private IAssignmentService assignmentService;

    @GetMapping("/assignment/add")
    public String addAssignment(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "addAssignment";
    }

    @GetMapping("/assignment/{id}")
    public ModelAndView getAssignment(@PathVariable long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject(assignment);
        mv.setViewName("showAssignment");

        return mv;
    }

    @GetMapping("/assignment/{id}/edit")
    public ModelAndView updateAssignment(@PathVariable long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject(assignment);
        mv.setViewName("updateAssignment");

        return mv;
    }

    @GetMapping("/assignment/{id}/delete")
    public ModelAndView deleteAssignment(@PathVariable long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        Course courseCode = assignment.getCourse();
        assignmentService.deleteAssignment(id);
        return new ModelAndView("redirect:/course/" + courseCode);
    }


}
