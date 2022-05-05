package com.example.enstruct.controller;

import com.example.enstruct.model.*;
import com.example.enstruct.service.IAssignmentService;
import com.example.enstruct.service.IClassesService;
import com.example.enstruct.service.IAttachmentService;
import com.example.enstruct.service.ISubmissionService;
import com.example.enstruct.service.IUserService;
import com.example.enstruct.util.AuthManager;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private IClassesService classesService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IUserService userService;

    @GetMapping("/assignment-add")
    public String addAssignment(Model model) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        model.addAttribute("assignment", new Assignment());
        model.addAttribute("courses", classesService.getClasses());
        return "addAssignment";
    }

    @PostMapping("/assignment-add")
    public String addAssignmentSubmit(@ModelAttribute Assignment assignment, Model model) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        model.addAttribute("assignment", assignment);
        assignment.setDeadline(assignment.getDeadline_date());
        Classes course = assignment.getCourse();
        assignmentService.addAssignment(assignment);
        return "redirect:/instructor/classes/" + course.getCourseCode();
    }

    @GetMapping("/assignment/{id}")
    public ModelAndView getAssignment(@PathVariable long id) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(authenticationResponse);
            return mv;
        }

        Assignment assignment = assignmentService.getAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("assignment", assignment);
        mv.setViewName("showAssignment");

        return mv;
    }

    @GetMapping("/assignment/{id}/edit")
    public ModelAndView updateAssignment(@PathVariable long id) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(authenticationResponse);
            return mv;
        }

        Assignment assignment = assignmentService.getAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("assignment", assignment);
        mv.setViewName("updateAssignment");


        return mv;
    }

    @PostMapping("/assignment/{id}/edit")
    public ModelAndView updateAssignmentSubmit(@PathVariable long id, @ModelAttribute Assignment assignment, Model model) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(authenticationResponse);
            return mv;
        }

        model.addAttribute("assignment", assignment);
        Classes course = assignment.getCourse();
        assignmentService.addAssignment(assignment);
        return new ModelAndView("redirect:/assignments/" + course.getCourseCode());

    }

    @PostMapping("/assignment/{id}/delete")
    public ModelAndView deleteAssignment(@PathVariable long id) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(authenticationResponse);
            return mv;
        }

        Assignment assignment = assignmentService.getAssignment(id);
        Classes course = assignment.getCourse();
        assignmentService.deleteAssignment(id);
        return new ModelAndView("redirect:/course/" + course.getCourseCode());
    }

    // get all submissions in assignment
    @GetMapping("/assignment/{id}/submissions")
    public ModelAndView getAllSubmissionsInAssignment(@PathVariable long id) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(authenticationResponse);
            return mv;
        }

        List<Submission> submissions = submissionService.getAllSubmissionsInAssignment(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("submissions", submissions);
        mv.setViewName("showSubmissions");

        return mv;
    }

    // get submission of student in assignment
    @GetMapping("/assignment/{assignmentId}/{studentNumber}")
    public ModelAndView getSubmissionOfStudentInAssignment(@PathVariable long assignmentId, @PathVariable long studentNumber) {

        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(authenticationResponse);
            return mv;
        }

        User user = AuthManager.getInstance().getLoggedInUser();

        Submission submission = submissionService.getSubmissionOfStudentInAssignment(studentNumber, assignmentId);
        ModelAndView mv = new ModelAndView("showSubmission");
        mv.addObject("submission", submission);
        mv.addObject("user", user);

        return mv;
    }


    //STUDENT SIDE
    @RequestMapping(value = "/student/assignment/{assignmentId}", method = RequestMethod.GET)
    public String showStudentAssignment(Model model, @PathVariable long assignmentId) {

        String authenticationResponse = AuthManager.getInstance().labelUser(false);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        AuthManager am = AuthManager.getInstance();
        User u = am.getLoggedInUser();


        Assignment a = assignmentService.getAssignment(assignmentId);


        //if nakapasa

        if(submissionService.getSubmissionOfStudentInAssignment(u.getUserId(), assignmentId) == null) {
            model.addAttribute("submitted", false);
        } else {
            model.addAttribute("submitted", true);
        }


        //if tapos na deadline
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();

        if(dateNow.isBefore(a.getDeadline_date())) {
            model.addAttribute("available", true);
        } else if(dateNow.isAfter(a.getDeadline_date())) {
            model.addAttribute("available", false);
        } else if(dateNow.isEqual(a.getDeadline_date())) {
            if (timeNow.isBefore(a.getDeadline_time())) {
                model.addAttribute("available", true);
            } else {
                model.addAttribute("available", false);
            }
        }



        model.addAttribute("instructions", a.getInstruction());

        LocalDate d = a.getDeadline_date();
        LocalTime t = a.getDeadline_time();

        String deadline = t.format(DateTimeFormatter.ofPattern("hh:mm a")) + ", " + d.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        model.addAttribute("assignmentName", a.getName());
        model.addAttribute("deadline", deadline);
        model.addAttribute("user", u);
        model.addAttribute("maxScore", a.getMaxScore());
        model.addAttribute("assignmentId", assignmentId);
        return "studentAssignment";
    }

    @RequestMapping(value = "/student/assignment/{assignmentId}/submit/{filename}", method = RequestMethod.POST)
    public ModelAndView showStudentAssignment(Model model, @PathVariable long assignmentId, @PathVariable String filename, @RequestParam("upload") MultipartFile file) {

        String fil = "C://enstruct//repository//";

        String authenticationResponse = AuthManager.getInstance().labelUser(false);
        if(authenticationResponse != "continue") {
            return new ModelAndView(authenticationResponse, (Map<String, ?>) model);
        }

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

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            fil += String.valueOf(assignmentId) + "//";
            Files.createDirectories(Paths.get(fil));
            Path path = Paths.get(fil + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


        s = submissionService.getLatestSubmission();

        return new ModelAndView("redirect:/student/submissions/" + s.getSubmissionId(), (Map<String, ?>) model);
    }

}
