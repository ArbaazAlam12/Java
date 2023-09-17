package com.example.demo.controller;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;
import com.example.demo.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody EmployeeEntity employeeEntity){
        try {
            return new ResponseEntity(employeeService.save(employeeEntity), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error saving the data",HttpStatus.OK);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        try {
            return new ResponseEntity(employeeService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error fetching all the data",HttpStatus.OK);
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable long id){
        try {
            return new ResponseEntity(employeeService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Unable to find id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
        public ResponseEntity update(@RequestBody EmployeeEntity employeeEntity){
            try {
                return new ResponseEntity(employeeService.update(employeeEntity), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity("Unable to update", HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity delete(@PathVariable Long id){
        try {
            employeeService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Incorrect Id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/allEmployee/{managerId}")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeByManagerId(@PathVariable long managerId){
        try {
            return new ResponseEntity<> (employeeService.findEmployeeByManagerId(managerId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error assigning the Id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assignToManager/{managerId}")
    public ResponseEntity<String> assignEmployeesToManager(
            @PathVariable("managerId") Long managerId,
            @RequestBody Map<String, List<Long>> requestBody) {
        try {
            List<Long> employeeId = requestBody.get("employeeId");
            employeeService.assignEmployeeToManagerId(employeeId, managerId);
            return ResponseEntity.ok("Employees assigned to manager successfully.");
        }catch (Exception e){
            return new ResponseEntity("Error assigning the Id", HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }
}
