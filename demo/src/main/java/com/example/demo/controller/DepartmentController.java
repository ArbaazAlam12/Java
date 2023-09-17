package com.example.demo.controller;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllDepartment() {
        try{
            return new ResponseEntity(departmentService.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity("Error finding the data", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody DepartmentEntity departmentEntity) {
        try {
            DepartmentEntity savedDepartment = departmentService.save(departmentEntity);
            return new ResponseEntity(savedDepartment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error saving the data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            return new ResponseEntity("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error deleting the department, Enter Correct ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            return new ResponseEntity(departmentService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error finding the Id, Enter Correct ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody DepartmentEntity departmentEntity) {
        try {
            return new ResponseEntity(departmentService.update(departmentEntity), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Error Updating the department, Enter Correct ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
