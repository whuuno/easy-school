package com.practice.easyschool.controller;

import com.practice.easyschool.model.Contact;
import com.practice.easyschool.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public ModelAndView saveMessage(Contact contact) {
        boolean status = contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }
}