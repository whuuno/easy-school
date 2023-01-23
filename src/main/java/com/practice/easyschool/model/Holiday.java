package com.practice.easyschool.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity{
    private String day;
    private String reason;
    private Type type;

    public enum Type {
        FEDERAL, FESTIVAL
    }
}
