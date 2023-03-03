package com.practice.easyschool.controller;

import com.practice.easyschool.model.Address;
import com.practice.easyschool.model.Person;
import com.practice.easyschool.model.Profile;
import com.practice.easyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/displayProfile", method = RequestMethod.GET)
    public ModelAndView displayProfile(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());

        if(person.getAddress() != null){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                              HttpSession session){

        if(errors.hasErrors()){
            return "profile.html";
        }
        Person person = (Person) session.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setMobileNumber(profile.getMobileNumber());
        person.setEmail(profile.getEmail());

        Address address = new Address();
        address.setAddress1(profile.getAddress1());
        address.setAddress2(profile.getAddress2());
        address.setCity(profile.getCity());
        address.setState(profile.getState());
        address.setZipCode(profile.getZipCode());

        if(person.getAddress() == null || person.getAddress().getAddressId()<=0){
            person.setAddress(new Address());
        }
        person.setAddress(address);
        personRepository.save(person);
        session.setAttribute("loggedInPerson", person);
        return "redirect:/displayProfile";
    }
}
