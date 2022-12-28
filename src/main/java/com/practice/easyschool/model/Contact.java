package com.practice.easyschool.model;

import lombok.Data;

@Data
public class Contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}