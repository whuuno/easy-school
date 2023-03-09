package com.practice.easyschool.controller;

import com.practice.easyschool.model.Person;
import com.practice.easyschool.repository.CoursesRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    CoursesRepository coursesRepository;

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);
        return modelAndView;
    }
}