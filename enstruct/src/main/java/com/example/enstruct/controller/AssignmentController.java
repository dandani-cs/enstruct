package com.example.enstruct.controller;

import com.example.enstruct.model.Assignment;
import com.example.enstruct.model.Classes;
import com.example.enstruct.model.Submission;
import com.example.enstruct.service.IAssignmentService;
import com.example.enstruct.service.IClassesService;
import com.example.enstruct.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AssignmentController {
    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ISubmissionService submissionService;

    @Autowired
    private IClassesService classesService;

    @GetMapping("/assignment-add")
    public String addAssignment(Model model) {
        model.addAttribute("assignment", new Assignment());
        model.addAttribute("courses", classesService.getClasses());
        return "addAssignment";
    }

    @PostMapping("/assignment-add")
    public String addAssignmentSubmit(@ModelAttribute Assignment assignment, Model model) {
        model.addAttribute("assignment", assignment);
        Classes course = assignment.getCourse();
        assignmentService.addAssignment(assignment);
        return "redirect:/assignments/";
    }

    @GetMapping("/assignment/{id}")
    public ModelAndView getAssignment(@PathVariable long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("assignment", assignment);
        mv.setViewName("showAssignment");

        return mv;
    }

    @GetMapping("/assignment/{id}/edit")
    public ModelAndView updateAssignment(@PathVariable long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("assignment", assignment);
        mv.setViewName("updateAssignment");

        return mv;
    }

    @PostMapping("/assignment/{id}/edit")
    public ModelAndView updateAssignmentSubmit(@PathVariable long id, @ModelAttribute Assignment assignment, Model model) {
        model.addAttribute("assignment", assignment);
        Classes course = assignment.getCourse();
        assignmentService.addAssignment(assignment);
        return new ModelAndView("redirect:/assignments/" + course.getCourseCode());

    }

    @PostMapping("/assignment/{id}/delete")
    public ModelAndView deleteAssignment(@PathVariable long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        Classes course = assignment.getCourse();
        assignmentService.deleteAssignment(id);
        return new ModelAndView("redirect:/course/" + course.getCourseCode());
    }

    // get all submissions in assignment
    @GetMapping("/assignment/{id}/submissions")
    public ModelAndView getAllSubmissionsInAssignment(@PathVariable long id) {
        List<Submission> submissions = submissionService.getAllSubmissionsInAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("submissions", submissions);
        mv.setViewName("showSubmissions");

        return mv;
    }

    // get submission of student in assignment
    @GetMapping("/assignment/{assignmentId}/{studentNumber}")
    public ModelAndView getSubmissionOfStudentInAssignment(@PathVariable long assignmentId, @PathVariable long studentNumber) {
        Submission submission = submissionService.getSubmissionOfStudentInAssignment(studentNumber, assignmentId);
        ModelAndView mv = new ModelAndView("showSubmission");
        mv.addObject("submission", submission);

        return mv;
    }

}
