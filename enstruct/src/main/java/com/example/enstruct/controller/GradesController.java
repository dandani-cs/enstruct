package com.example.enstruct.controller;

import com.example.enstruct.model.*;
import com.example.enstruct.service.AssignmentService;
import com.example.enstruct.service.ClassesService;
import com.example.enstruct.service.EnrollmentService;
import com.example.enstruct.service.SubmissionService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradesController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ClassesService classesService;

    @GetMapping("/student/grades")
    public ModelAndView studentGradesView() {
        User user = AuthManager.getInstance().getLoggedInUser();

        List<Enrollment> en = enrollmentService.findByUserId(user.getUserId());

        List<Assignment> assignments = assignmentService.getAssignmentsInCourse(en.get(0).getCourseCode().getCourseCode());

        ModelAndView mv = new ModelAndView();
        mv.addObject("assignments", assignments);

        ArrayList<Submission> submissions = new ArrayList<>();

        for (Assignment a :
                assignments) {
            submissions.add(submissionService.getSubmissionOfStudentInAssignment(user.getUserId(), a.getAssignmentId()));
        }

        mv.addObject("submission", submissions);
        mv.setViewName("studentGrades");

        return mv;
    }

    @GetMapping("/instructor/grades")
    public String instructorGradesClassesView(Model model) {
        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Classes> c = classesService.getClassesByTeacherId(user.getUserId());
        System.out.println("CSIZE: "+ c.size());

        List<String> courseCode = new ArrayList<>();
        List<String> section = new ArrayList<>();
        List<String> professor = new ArrayList<>();

        for(int i = 0; i < c.size(); i++) {

            String cc = c.get(i).getCourseCode();

            int courseCodeIndex = cc.indexOf("_");
            int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

            courseCode.add(cc.substring(0, courseCodeIndex));
            section.add(cc.substring(courseCodeIndex+1, sectionIndex));

            User teacher = c.get(i).getTeacherId();

            String teacherName = null;
            if(teacher.getFirstName() != null) {
                teacherName = teacher.getFirstName();
            }
            if(teacher.getMiddleName() != null) {
                teacherName += " " + teacher.getMiddleName();
            }
            if(teacher.getLastName() != null) {
                teacherName += " " + teacher.getLastName();
            }

            professor.add(teacherName);
        }

        model.addAttribute("classes", c);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
        model.addAttribute("professor", professor);
        return "instructorGradesClasses";
    }

    @GetMapping("/instructor/grades/{courseCode}")
    public ModelAndView instructorGradesView(@PathVariable String courseCode) {
        List<Enrollment> enrollments = enrollmentService.findByCourseId(courseCode);
        List<Assignment> assignments = assignmentService.getAssignmentsInCourse(courseCode);

        List<Object> rows = new ArrayList<Object>();
        List<Object> row = new ArrayList<Object>();

        for (Enrollment e :
                enrollments) {
            row.add(e.getUser().getLastName());
            row.add(e.getUser().getFirstName());
            for (Assignment a :
                    assignments) {
                Submission submission = submissionService.getSubmissionOfStudentInAssignment(e.getUser().getUserId(), a.getAssignmentId());
                if (submission != null) {
                    row.add(submission.getGrade());
                } else {
                    row.add("N/A");
                }

            }
            rows.add(row);
        }

        ModelAndView mv = new ModelAndView("instructorGrades");
        mv.addObject("rows", rows);
        return mv;
    }
}
