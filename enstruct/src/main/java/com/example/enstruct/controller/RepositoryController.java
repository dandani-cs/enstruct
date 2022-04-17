package com.example.enstruct.controller;

import com.example.enstruct.model.*;
import com.example.enstruct.service.*;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RepositoryController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ISubmissionService submissionService;

    @Autowired
    private IAttachmentService attachmentService;

    @RequestMapping(value = "/student/repositories", method = RequestMethod.GET)
    public String showStudentRepositories(Model model)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Enrollment> e = enrollmentService.findByUserId(user.getUserId());
        System.out.println("ESIZE: "+ e.size());

        List<String> courseCode = new ArrayList<>();
        List<String> section = new ArrayList<>();

        List<Assignment> a = new ArrayList<>();
        for(int i = 0; i < e.size(); i++) {
            String cc = e.get(i).getCourseCode().getCourseCode();

            System.out.println("CC: " + cc);
            List<Assignment> assignments = assignmentService.getAssignmentsInCourse(cc);
            int courseCodeIndex = cc.indexOf("_");
            int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

            for(int j = 0; j < assignments.size(); j++) {
                a.add(assignments.get(j));
                courseCode.add(cc.substring(0, courseCodeIndex));
                section.add(cc.substring(courseCodeIndex+1, sectionIndex));
            }
        }

        model.addAttribute("repositories", a);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        return "studentRepositories";
    }

    @RequestMapping(value = "/instructor/repositories", method = RequestMethod.GET)
    public String showInstructorRepositories(Model model)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Classes> c = classesService.getClassesByTeacherId(user.getUserId());

        List<String> courseCode = new ArrayList<>();
        List<String> section = new ArrayList<>();

        List<Assignment> a = new ArrayList<>();
        for(int i = 0; i < c.size(); i++) {
            String cc = c.get(i).getCourseCode();

            System.out.println("CC: " + cc);
            List<Assignment> assignments = assignmentService.getAssignmentsInCourse(cc);
            int courseCodeIndex = cc.indexOf("_");
            int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

            for(int j = 0; j < assignments.size(); j++) {
                a.add(assignments.get(j));
                courseCode.add(cc.substring(0, courseCodeIndex));
                section.add(cc.substring(courseCodeIndex+1, sectionIndex));
            }
        }

        model.addAttribute("repositories", a);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        return "instructorRepositories";
    }

    @RequestMapping(value = "/student/repositories/{assignmentId}", method = RequestMethod.GET)
    public String showStudentInnerRepo(Model model, @PathVariable Long assignmentId)
    {
        AuthManager am = AuthManager.getInstance();
        User u = am.getLoggedInUser();

        Assignment a = assignmentService.getAssignment(assignmentId);

        String cc = a.getCourse().getCourseCode();

        int courseCodeIndex = cc.indexOf("_");
        int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

        String courseCode = cc.substring(0, courseCodeIndex);
        String section = cc.substring(courseCodeIndex+1, sectionIndex);

        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        model.addAttribute("name", a.getName());

        List<Submission> s = submissionService.getAllSubmissionsInAssignment(assignmentId);

        List<String> students = new ArrayList<>();
        List<String> filenames = new ArrayList<>();

        boolean showAddFiles = true;
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

            if(user.getUserId() == u.getUserId()) {
                showAddFiles = false;
            }

            students.add(userName);
            System.out.println("uName" + userName);
            filenames.add(s.get(i).getAttachment().getFilename());
        }

        model.addAttribute("submissions", s);
        model.addAttribute("students", students);
        model.addAttribute("filenames", filenames);
        model.addAttribute("showAddFiles", showAddFiles);
        return "studentInnerRepo";
    }

    @RequestMapping(value = "/instructor/repositories/{assignmentId}", method = RequestMethod.GET)
    public String showInstructorInnerRepo(Model model, @PathVariable Long assignmentId)
    {
        Assignment a = assignmentService.getAssignment(assignmentId);

        String cc = a.getCourse().getCourseCode();

        int courseCodeIndex = cc.indexOf("_");
        int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

        String courseCode = cc.substring(0, courseCodeIndex);
        String section = cc.substring(courseCodeIndex+1, sectionIndex);

        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        model.addAttribute("name", a.getName());

        List<Submission> s = submissionService.getAllSubmissionsInAssignment(assignmentId);

        List<String> students = new ArrayList<>();
        List<String> filenames = new ArrayList<>();

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

            students.add(userName);
            filenames.add(s.get(i).getAttachment().getFilename());
        }

        model.addAttribute("submissions", s);
        model.addAttribute("students", students);
        model.addAttribute("filenames", filenames);
        return "instructorInnerRepo";
    }
}