package com.practice.easyschool.services;

import com.practice.easyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        //TODO - Need to persist data into the DB Table
        //Assumption - data is saved successfully
        log.info(contact.toString());
        return isSaved;
    }
}
