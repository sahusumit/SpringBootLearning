package com.example.springbootlearning.controllerandMapping;

import java.util.Date;

public class User {
    private String firstname;
    private Date dob;
    private int age;
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
