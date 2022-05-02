package com.example.enstruct.controller;

import com.example.enstruct.model.Announcement;
import com.example.enstruct.model.Assignment;
import com.example.enstruct.model.Classes;
import com.example.enstruct.model.User;
import com.example.enstruct.repository.ClassesRepository;
import com.example.enstruct.service.AnnouncementService;
import com.example.enstruct.service.AssignmentService;
import com.example.enstruct.util.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AssignmentService assignmentService;

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

        Calendar cal    = Calendar.getInstance();

        Date date_today = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int  num_days_mo   = cal.getActualMaximum(Calendar.DATE);
        int  weekday_start = cal.get(Calendar.DAY_OF_WEEK);

        Date month_start = cal.getTime();
        cal.add(Calendar.DATE, num_days_mo);
        Date month_end = cal.getTime();

        List<Assignment> assignments_month   = assignmentService.getAllAssignmentsWithinDates(month_start, month_end);
        List<Assignment> pending_assignments = assignmentService.getAllPendingAssignments();

        ArrayList<List<Assignment>[]> to_display = new ArrayList<>();


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
                ArrayList<Assignment> filtered = new ArrayList<>(assignments_month.stream().filter(within_day).collect(Collectors.toList()));
                calendar_table[week_idx][day_idx] = filtered.isEmpty() ? null : filtered;

                cal.add(Calendar.DATE, 1);

            }

        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("announcements", announcements);
        mv.addObject("calendar_table", calendar_table);
        mv.addObject("days_in_mo", num_days_mo);
        mv.addObject("weekday_start", weekday_start);
        mv.addObject("assignments", pending_assignments);
        mv.setViewName("studentAnnouncements");
        return mv;
    }
}
