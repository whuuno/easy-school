package com.practice.easyschool.controller;

import com.practice.easyschool.model.Person;
//import com.practice.easyschool.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

}