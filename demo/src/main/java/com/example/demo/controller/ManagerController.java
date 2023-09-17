package com.example.demo.controller;

import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    @GetMapping("/findAll")
    public ResponseEntity findAllManager(){
        return new ResponseEntity(managerService.FindAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteManager(@PathVariable Long id){
        try {
            return new ResponseEntity(deleteManager(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Cannot find the id to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/save")
    public ResponseEntity saveManager(@RequestBody ManagerEntity managerEntity){
        return new ResponseEntity(managerService.save(managerEntity), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findByIdManager(@PathVariable Long id){
        try {
            return new ResponseEntity(managerService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Cannot find the id entered", HttpStatus.OK);
        }

    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ManagerEntity managerEntity){
        return new ResponseEntity(managerService.update(managerEntity), HttpStatus.OK);
    }

    @GetMapping("/inDepartments")
    public List<ManagerEntity> getManagersInDepartments() {
        return managerService.findManagersInDepartments();
    }

    @GetMapping("/inDepartments/{departmentId}")
    public List<ManagerEntity> getManagersInDepartmentById(@PathVariable Long departmentId) {
        return managerService.findManagersInDepartmentById(departmentId);
    }
}
