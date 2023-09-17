package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.*;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.utility.CustomerMapperEmployee;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeService(EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.employeeRepository = employeeRepository;
        this.entityManager=entityManager;
    }

    public EmployeeEntity save(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    public List<EmployeeEntity> findAll(){
        return employeeRepository.findAll();
    }

    public EmployeeDTO findById(Long id){
        EmployeeDTO employeeDTO= CustomerMapperEmployee.convertToManagerDto(employeeRepository.findById(id).get());
        return employeeDTO;
   }

   public EmployeeEntity update(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
   }


    public List<EmployeeEntity> findEmployeeByManagerId(Long managerId) {
        JPAQuery<EmployeeEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity qManagerEntity = QManagerEntity.managerEntity;
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        return query.select(qEmployeeEntity)
                .from(qEmployeeEntity)
                .innerJoin(qEmployeeEntity.manager, qManagerEntity)
                .where(qManagerEntity.id.eq(managerId))
                .fetch();
    }

    @Transactional
    public List<EmployeeEntity> assignEmployeeToManagerId(List<Long> employeeId, Long managerId) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        new JPAUpdateClause(entityManager, qEmployeeEntity)
                .set(qEmployeeEntity.manager.id, managerId)
                .where(qEmployeeEntity.id.in(employeeId))
                .execute();

//        JPAQuery<EmployeeEntity> query = new JPAQuery<>(entityManager);
//        new JPAUpdateClause(entityManager, qEmployeeEntity)
//                .set(qEmployeeEntity.manager, qManagerEntity)
//                .where(qEmployeeEntity.id.in(employeeId))
//                .execute();

        return new JPAQuery<>(entityManager)
                .select(qEmployeeEntity)
                .from(qEmployeeEntity)
                .where(qEmployeeEntity.id.in(employeeId))
                .fetch();
    }
}
