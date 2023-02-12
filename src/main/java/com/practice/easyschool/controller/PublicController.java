package com.practice.easyschool.controller;

import com.practice.easyschool.model.Person;
import com.practice.easyschool.services.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegistrationForm(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createNewUser(@Valid @ModelAttribute(name = "person") Person person, Errors error){
        if(error.hasErrors()){
            return "register.html";
        }
        boolean isSaved = personService.createNewPerson(person);
        if(!isSaved){
            return "register.html";
        }
        return "redirect:/login?register=true";
    }

}