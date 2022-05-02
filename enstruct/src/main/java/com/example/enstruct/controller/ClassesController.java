package com.example.enstruct.controller;

import com.example.enstruct.model.Assignment;
import com.example.enstruct.model.Classes;
import com.example.enstruct.model.Enrollment;
import com.example.enstruct.model.User;
import com.example.enstruct.service.IAssignmentService;
import com.example.enstruct.service.IClassesService;
import com.example.enstruct.service.IEnrollmentService;
import com.example.enstruct.service.IUserService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class ClassesController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IClassesService classesService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAssignmentService assignmentService;

    @RequestMapping(value = "/student/classes", method = RequestMethod.GET)
    public String showStudentClasses(Model model)
    {
        String authenticationResponse = AuthManager.getInstance().labelUser(false);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Enrollment> e = enrollmentService.findByUserId(user.getUserId());
        System.out.println("ESIZE: "+ e.size());

        List<Classes> c = new ArrayList<>();
        List<String> courseCode = new ArrayList<>();
        List<String> section = new ArrayList<>();
        List<String> professor = new ArrayList<>();

        for(int i = 0; i < e.size(); i++) {
            c.add(e.get(i).getCourseCode());

            System.out.println("E: " + e.get(i));
            System.out.println("CC1: " + e.get(i).getCourseCode());
            System.out.println("CC2: " + e.get(i).getCourseCode().getCourseCode());
            String cc = e.get(i).getCourseCode().getCourseCode();

            int courseCodeIndex = cc.indexOf("_");
            int sectionIndex = cc.indexOf("_", courseCodeIndex+1);

            courseCode.add(cc.substring(0, courseCodeIndex));
            section.add(cc.substring(courseCodeIndex+1, sectionIndex));

//            User teacher = e.get(i).getCourseCode().getTeacherId();
//
//            String teacherName = null;
//            if(teacher.getFirstName() != null) {
//                teacherName = teacher.getFirstName();
//            }
//            if(teacher.getMiddleName() != null) {
//                teacherName += " " + teacher.getMiddleName();
//            }
//            if(teacher.getLastName() != null) {
//                teacherName += " " + teacher.getLastName();
//            }
//
//            professor.add(teacherName);
        }

        model.addAttribute("classes", c);
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("section", section);
//        model.addAttribute("professor", professor);

        return "studentClasses";
    }

    @RequestMapping(value = "/student/classes/{courseCode}")
    public ModelAndView viewStudentClassDashboard(ModelMap model, @PathVariable String courseCode)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        if(user == null || user.getTeacher()) {
            return new ModelAndView("redirect:/login", model);
        }

        Classes course = classesService.getClass(courseCode);

        if(courseCode.isEmpty() || course == null) {
            return new ModelAndView("redirect:/student", model);
        }

        Calendar cal    = Calendar.getInstance();

        Date date_today = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int  num_days_mo   = cal.getActualMaximum(Calendar.DATE);
        int  weekday_start = cal.get(Calendar.DAY_OF_WEEK);

        Date month_start = cal.getTime();
        cal.add(Calendar.DATE, num_days_mo);
        Date month_end = cal.getTime();

        List<Assignment> assignments_in_month = assignmentService.getAllAssignmentsWithinDatesByCourseCode(month_start, month_end, course.getCourseCode());
        List<Assignment> assignments_from_now = assignmentService.getPendingAssignmentsByCourseCode(course.getCourseCode());

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, date_today.getMonth());

        int total_days = num_days_mo + weekday_start - 1;
        int total_rows = (int) Math.ceil(total_days / 7.0);

        ArrayList<Assignment>[][] calendar_table = new ArrayList[total_rows][7];
        System.out.println("DAYS IN MO: " + num_days_mo);
        System.out.println("TOTAL DAYS: " + total_days);
        System.out.println("TOTAL ROWS: " + total_rows);

        for(int week_idx = 0; week_idx < total_rows; week_idx++)
        {
            for(int day_idx = 0; day_idx < 7; day_idx++)
            {
                int curr_day_idx = (week_idx * 7) + day_idx;

                if(curr_day_idx < weekday_start - 1)
                {
                    calendar_table[week_idx][day_idx] = null;
                    continue;
                }

                Date curr_date = cal.getTime();

                Predicate<Assignment> within_day = assignment -> assignment.getDeadline_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(fmt.format(curr_date));
                ArrayList<Assignment> filtered = new ArrayList<>(assignments_in_month.stream().filter(within_day).collect(Collectors.toList()));
                calendar_table[week_idx][day_idx] = filtered.isEmpty() ? null : filtered;

                cal.add(Calendar.DATE, 1);

            }

        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("course", course);
        mv.addObject("calendar_table", calendar_table);
        mv.addObject("days_in_mo", num_days_mo);
        mv.addObject("weekday_start", weekday_start);
        mv.addObject("assignments_from_now", assignments_from_now);
        mv.setViewName("studentClassDashboard");

        return mv;
    }

    @RequestMapping(value = "/instructor/classes", method = RequestMethod.GET)
    public String showInstructorClasses(Model model)
    {
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

        return "instructorClasses";
    }

    @RequestMapping(value = "/instructor/classes/{courseCode}")
    public ModelAndView viewIstructorClassDashboard(ModelMap model, @PathVariable String courseCode)
    {
        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        if(user == null || !user.getTeacher()) {
            return new ModelAndView("redirect:/login", model);
        }

        Classes course = classesService.getClass(courseCode);

        if(courseCode.isEmpty() || course == null) {
            return new ModelAndView("redirect:/instructor", model);
        }

        Calendar cal    = Calendar.getInstance();

        Date date_today = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int  num_days_mo   = cal.getActualMaximum(Calendar.DATE);
        int  weekday_start = cal.get(Calendar.DAY_OF_WEEK);

        Date month_start = cal.getTime();
        cal.add(Calendar.DATE, num_days_mo);
        Date month_end = cal.getTime();

        List<Assignment> assignments_in_month = assignmentService.getAllAssignmentsWithinDatesByCourseCode(month_start, month_end, course.getCourseCode());
        List<Assignment> assignments_from_now = assignmentService.getPendingAssignmentsByCourseCode(course.getCourseCode());

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, date_today.getMonth());

        int total_days = num_days_mo + weekday_start - 1;
        int total_rows = (int) Math.ceil(total_days / 7.0);

        ArrayList<Assignment>[][] calendar_table = new ArrayList[total_rows][7];
        System.out.println("DAYS IN MO: " + num_days_mo);
        System.out.println("TOTAL DAYS: " + total_days);
        System.out.println("TOTAL ROWS: " + total_rows);

        for(int week_idx = 0; week_idx < total_rows; week_idx++)
        {
            for(int day_idx = 0; day_idx < 7; day_idx++)
            {
                int curr_day_idx = (week_idx * 7) + day_idx;

                if(curr_day_idx < weekday_start - 1)
                {
                    calendar_table[week_idx][day_idx] = null;
                    continue;
                }

                Date curr_date = cal.getTime();

                Predicate<Assignment> within_day = assignment -> assignment.getDeadline_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(fmt.format(curr_date));
                ArrayList<Assignment> filtered = new ArrayList<>(assignments_in_month.stream().filter(within_day).collect(Collectors.toList()));
                calendar_table[week_idx][day_idx] = filtered.isEmpty() ? null : filtered;

                cal.add(Calendar.DATE, 1);

            }

        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("course", course);
        mv.addObject("calendar_table", calendar_table);
        mv.addObject("days_in_mo", num_days_mo);
        mv.addObject("weekday_start", weekday_start);
        mv.addObject("assignments_from_now", assignments_from_now);
        mv.setViewName("instructorClassDashboard");

        return mv;
    }

    @RequestMapping(value = "/instructor/classes/addClass", method = RequestMethod.GET)
    public String addClass(Model model)
    {
        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            return authenticationResponse;
        }

        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        List<Classes> c = classesService.getClasses();
        List<User> u = userService.findAllStudents();


        model.addAttribute("classes", c);
        model.addAttribute("students", u);

        return "instructorAddClass";
    }

    @RequestMapping(value = "/instructor/classes/addClass/{courseCode}/{courseName}/{enabled}/{students}", method = RequestMethod.POST)
    public ModelAndView addClass(Model model, @PathVariable String courseCode, @PathVariable String courseName, @PathVariable Boolean enabled, @PathVariable String students)
    {
        String authenticationResponse = AuthManager.getInstance().labelUser(true);
        if(authenticationResponse != "continue") {
            return new ModelAndView(authenticationResponse, (Map<String, ?>) model);
        }

        AuthManager am = AuthManager.getInstance();
        User user = am.getLoggedInUser();

        Classes c = new Classes();
        c.setCourseCode(courseCode);
        c.setCourseName(courseName);
        c.setEnabled(enabled);
        c.setTeacherId(user);

        classesService.addClass(c);

        System.out.println("STUDENTS: " + students);

        List<Long> enrollees = new ArrayList<>();

        if(!students.equals("-1")) {
            try{
                while(true) {
                    enrollees.add(Long.valueOf(students.substring(0, students.indexOf("."))));
                    students = students.substring(students.indexOf(".")+1);
                }
            } catch(Exception e) {}
        }

        for(int i = 0; i < enrollees.size(); i++) {
            Enrollment e = new Enrollment();
            e.setCourseCode(c);
            e.setUser(userService.getUser(enrollees.get(i)));
            enrollmentService.addEnrollment(e);
        }

        return new ModelAndView("redirect:/instructor/classes", (Map<String, ?>) model);
    }
}
