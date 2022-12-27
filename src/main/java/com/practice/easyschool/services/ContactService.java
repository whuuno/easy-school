package com.practice.easyschool.services;

import com.practice.easyschool.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        //TODO - Need to persist data into the DB Table
        //Assumption - data is saved successfully
        log.info(contact.toString());
        return isSaved;
    }
}
