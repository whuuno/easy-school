package com.practice.easyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = {"","/","/home"})
    public String displayHomePage() {
        return "home.html";
    }

    @RequestMapping(value = {"/courses"})
    public String displayCoursePage() {
        return "courses.html";
    }

    @RequestMapping(value = {"/contact"})
    public String displayContactPage() {
        return "contact.html";
    }
}
