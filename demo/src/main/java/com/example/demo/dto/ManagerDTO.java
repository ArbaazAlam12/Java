package com.example.demo.dto;

import com.example.demo.entities.DepartmentEntity;

import java.util.List;

public class ManagerDTO {
    private long id;
    private String name;

    private String email;
    private String Contact;
    private DepartmentEntity department_id;

    public ManagerDTO(long id, String name, String email, String contact, DepartmentEntity department_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        Contact = contact;
        this.department_id = department_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public DepartmentEntity getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(DepartmentEntity department_id) {
        this.department_id = department_id;
    }
}
