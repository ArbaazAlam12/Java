package com.example.demo.entities;

import jakarta.persistence.*;
import org.apache.naming.factory.SendMailFactory;

@Entity
@Table(name="Manager")
public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department_id;

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

    public DepartmentEntity getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(DepartmentEntity department_id) {
        this.department_id = department_id;
    }
}

