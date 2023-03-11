package com.practice.easyschool.services;

import com.practice.easyschool.constants.EazySchoolConstants;
import com.practice.easyschool.model.Contact;
import com.practice.easyschool.repository.ContactRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Getter
@Setter
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        //TODO - Need to persist data into the DB Table
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        Contact savedContact = contactRepository.save(contact);

        if(savedContact == null) isSaved = false;
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1,
                pageSize, (sortDir == "asc") ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending());
        Page<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN, pageable);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent( contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });

        Contact savedContact = contactRepository.save(contact.get());
        if(savedContact != null && savedContact.getUpdatedBy()!=null) isUpdated = true;
        return isUpdated;
    }
}
