package com.example.enstruct.controller;

import com.example.enstruct.model.*;
import com.example.enstruct.service.IAssignmentService;
import com.example.enstruct.service.IAttachmentService;
import com.example.enstruct.service.ISubmissionService;
import com.example.enstruct.service.IUserService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AssignmentController {
    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ISubmissionService submissionService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IUserService userService;

    @GetMapping("/assignment/add")
    public String addAssignment(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "addAssignment";
    }

    @PostMapping("/assignment/add")
    public String addAssignmentSubmit(@ModelAttribute Assignment assignment, Model model) {
        model.addAttribute("assignment", assignment);
        Classes course = assignment.getCourse();
        assignmentService.addAssignment(assignment);
        return "redirect:/assignments/" + course.getCourseCode();
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


    //STUDENT SIDE
    @RequestMapping(value = "/student/assignment/{assignmentId}", method = RequestMethod.GET)
    public String showStudentAssignment(Model model, @PathVariable long assignmentId) {
        System.out.println("IN HERE");
        Assignment a = assignmentService.getAssignment(assignmentId);


        model.addAttribute("instructions", a.getInstruction());

        Date d = a.getDeadline();
        DateFormat df = DateFormat.getDateInstance();
        String deadline = df.format(d);
        System.out.println("DEADLINE: " + deadline);
        model.addAttribute("deadline", a.getDeadline());
        model.addAttribute("maxScore", a.getMaxScore());
        model.addAttribute("assignmentId", assignmentId);
        return "studentAssignment";
    }

    @RequestMapping(value = "/student/assignment/{assignmentId}/submit/{filename}", method = RequestMethod.POST)
    public ModelAndView showStudentAssignment(Model model, @PathVariable long assignmentId, @PathVariable String filename) {

        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        User u = userService.getUser(user.getUserId());
        Assignment assignment = assignmentService.getAssignment(assignmentId);
        Submission s = new Submission();
        Attachment a = new Attachment();
        a.setFilename(filename);
        a.setOwnerId(u);


        attachmentService.addAttachment(a);


        a = attachmentService.getInsertedAttachment(a.getFilename(), user.getUserId());


        s.setAssignmentId(assignment);
        s.setSubmissionDate(new Date());
        s.setCourse(assignment.getCourse());
        s.setAttachment(a);
        s.setStudentNumber(u);

        submissionService.addSubmission(s);

        s = submissionService.getLatestSubmission();

        return new ModelAndView("redirect:/student/submissions/" + s.getSubmissionId(), (Map<String, ?>) model);
    }

}
