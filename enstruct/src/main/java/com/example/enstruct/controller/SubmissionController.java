package com.example.enstruct.controller;
import com.example.enstruct.model.*;
import com.example.enstruct.service.IClassesService;
import com.example.enstruct.service.IEnrollmentService;
import com.example.enstruct.service.ISubmissionService;
import com.example.enstruct.service.IUserService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SubmissionController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISubmissionService submissionService;

    @RequestMapping(value = "/student/submissions/{submissionId}", method = RequestMethod.GET)
    public String showStudentSubmissions(Model model, @PathVariable long submissionId) throws IOException {
        String authenticationResponse = AuthManager.getInstance().labelUser(false);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        AuthManager am = AuthManager.getInstance();
        User u = am.getLoggedInUser();


        Assignment a = null;
        List<Submission> s = new ArrayList<>();
        try{

        a = submissionService.getSubmission(submissionId).getAssignmentId();
        s = submissionService.getAllSubmissionsInAssignment(a.getAssignmentId());
        } catch(Exception e) {
            return "redirect:/student";
        }


        Submission ss = submissionService.getSubmissionOfStudentInAssignment(u.getUserId(), a.getAssignmentId());
        if(ss != null) {
            if(ss.getSubmissionId() != submissionId) {
                return "redirect:/student/submissions/" + ss.getSubmissionId();
            }
        } else {
            return "redirect:/student";
        }


        String cc = a.getCourse().getCourseCode();

        int courseCodeIndex = cc.indexOf("_");
        int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

        String courseCode = cc.substring(0, courseCodeIndex);
        String section = cc.substring(courseCodeIndex+1, sectionIndex);

        Double grade = submissionService.getSubmission(submissionId).getGrade();
        if(grade != null) {
            model.addAttribute("grade", grade);
        } else {
            model.addAttribute("grade", "");
        }

        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        model.addAttribute("name", a.getName());

        List<String> students = new ArrayList<>();
        String filename = submissionService.getSubmission(submissionId).getAttachment().getFilename();
        filename = filename.trim();

        long prev = submissionId, next = submissionId;
        for(int i = 0; i < s.size(); i++) {
            User user = s.get(i).getStudentNumber();

            String userName = "";
            if(user.getFirstName() != null) {
                userName = user.getFirstName();
            }
            if(user.getMiddleName() != null) {
                userName += " " + user.getMiddleName();
            }
            if(user.getLastName() != null) {
                userName += " " + user.getLastName();
            }

            if(submissionService.getSubmission(submissionId).getStudentNumber().getUserId() == user.getUserId()) {
                model.addAttribute("student", userName);
                if(i > 0) {
                    prev = s.get(i-1).getSubmissionId();
                }

                if(i < s.size() -1) {
                    next = s.get(i+1).getSubmissionId();
                }
            }
            students.add(userName);
        }

        String fil = "C://enstruct//repository//" + String.valueOf(a.getAssignmentId()) + "//" + filename;

        FileInputStream fin = new FileInputStream(fil);
        int i = 0;
        String code = "";
        while((i = fin.read()) != -1){
            if('"' == (char) i) {
                code += "\\\"" ;
            } else if('\n' == (char) i) {
                code += "\\" + "n";
            } else if('\\' == (char) i) {
                code += "\\\\";
            } else if('\r' == (char) i) {
            } else {
                code += (char) i;
            }
        }
        fin.close();

        System.out.println("CODE:"+code);

        model.addAttribute("students", students);
        model.addAttribute("submissions", s);
        model.addAttribute("filename", filename);
        model.addAttribute("code", code);
        model.addAttribute("previous", prev);
        model.addAttribute("next", next);
        return "studentSubmissions";
    }

    @RequestMapping(value = "/instructor/submissions/{submissionId}", method = RequestMethod.GET)
    public String showInstructorSubmissions(Model model, @PathVariable long submissionId) throws IOException {
        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        Assignment a = submissionService.getSubmission(submissionId).getAssignmentId();
        List<Submission> s = submissionService.getAllSubmissionsInAssignment(a.getAssignmentId());

        String cc = a.getCourse().getCourseCode();

        int courseCodeIndex = cc.indexOf("_");
        int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

        String courseCode = cc.substring(0, courseCodeIndex);
        String section = cc.substring(courseCodeIndex+1, sectionIndex);

        Double grade = submissionService.getSubmission(submissionId).getGrade();
        if(grade != null) {
            model.addAttribute("grade", grade);
        } else {
            model.addAttribute("grade", "");
        }

        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        model.addAttribute("name", a.getName());
        model.addAttribute("maxScore", a.getMaxScore());

        List<String> students = new ArrayList<>();
        String filename = submissionService.getSubmission(submissionId).getAttachment().getFilename();
        filename = filename.trim();

        long prev = submissionId, next = submissionId;
        for(int i = 0; i < s.size(); i++) {
            User user = s.get(i).getStudentNumber();

            String userName = "";
            if(user.getFirstName() != null) {
                userName = user.getFirstName();
            }
            if(user.getMiddleName() != null) {
                userName += " " + user.getMiddleName();
            }
            if(user.getLastName() != null) {
                userName += " " + user.getLastName();
            }

            if(submissionService.getSubmission(submissionId).getStudentNumber().getUserId() == user.getUserId()) {
                model.addAttribute("student", userName);
                if(i > 0) {
                    prev = s.get(i-1).getSubmissionId();
                }

                if(i < s.size() -1) {
                    next = s.get(i+1).getSubmissionId();
                }
            }
            students.add(userName);
        }
        String fil = "C://enstruct//repository//" + String.valueOf(a.getAssignmentId()) + "//" + filename;
        FileInputStream fin = new FileInputStream(fil);
        int i = 0;
        String code = "";
        while((i = fin.read()) != -1){
            if('"' == (char) i) {
                code += "\\\"" ;
            } else if('\n' == (char) i) {
                code += "\\" + "n";
            } else if('\\' == (char) i) {
                code += "\\\\";
            } else if('\r' == (char) i) {
            } else {
                code += (char) i;
            }
        }
        fin.close();

        System.out.println("CODE:"+code);

        model.addAttribute("students", students);
        model.addAttribute("submissions", s);
        model.addAttribute("filename", filename);
        model.addAttribute("code", code);
        model.addAttribute("previous", prev);
        model.addAttribute("next", next);
        return "instructorSubmissions";
    }
}
