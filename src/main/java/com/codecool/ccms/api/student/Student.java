package com.codecool.ccms.api.student;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by bujak on 12.07.17.
 */

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotEmpty(message = "firstName.error")
    private String firstName;

    @Column
    @NotEmpty(message = "lastName.error")
    private String lastName;

    @Column
    @NotEmpty(message = "klass.error")
    private String klass;

    public Student(String firstName, String lastName, String klass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.klass = klass;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer  id) {
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
