package com.codecool.ccms.api.student;

import javax.persistence.Entity;

/**
 * Created by bujak on 12.07.17.
 */

@Entity
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String klass;

    public Student(String firstName, String lastName, String klass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.klass = klass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }
}
