package com.practice.easyschool.controller;

import com.practice.easyschool.model.Person;
import com.practice.easyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(person.getSchoolClass() != null || person.getSchoolClass().getClassId()>0){
            model.addAttribute("enrolledClass",person.getSchoolClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
