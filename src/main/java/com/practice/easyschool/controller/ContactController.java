package com.practice.easyschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);
    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam(name = "mobileNum") String mobileNumber, @RequestParam String email,
                                    @RequestParam String subject, @RequestParam String message) {
        log.info("name = " + name + "/n mobileNumber = " + mobileNumber + "/n email = " + email + "/n subject = " + subject + "/n message = " + message);
        return new ModelAndView("redirect:/contact");
    }
}