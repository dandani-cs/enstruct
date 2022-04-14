package com.example.enstruct.controller;

import com.example.enstruct.model.Enrollment;
import com.example.enstruct.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {

    @Autowired
    private IEnrollmentService service;


}
