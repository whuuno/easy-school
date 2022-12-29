package com.practice.easyschool.services;

import com.practice.easyschool.model.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//import org.springframework.web.context.annotation.ApplicationScope;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
@Getter
@Setter
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {
    private int counter = 0;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        //TODO - Need to persist data into the DB Table
        //Assumption - data is saved successfully
        log.info(contact.toString());
        return isSaved;
    }
}
