package com.example.demo.dto;


import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.ManagerEntity;

import java.util.List;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String contact;
    private DepartmentEntity department;
    private ManagerEntity manager;
//    private List<Long> employeeId;
//
//    public List<Long> getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(List<Long> employeeId) {
//        this.employeeId = employeeId;
//    }

    public EmployeeDTO(Long id, String name, String email, String contact, DepartmentEntity department, ManagerEntity manager) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.department = department;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public ManagerEntity getManager() {
        return manager;
    }

    public void setManager(ManagerEntity manager) {
        this.manager = manager;
    }
}
