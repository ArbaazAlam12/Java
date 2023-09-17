package com.example.demo.services;

import com.example.demo.dto.ManagerDTO;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.entities.QDepartmentEntity;
import com.example.demo.entities.QManagerEntity;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.utility.CustomerMapperManager;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {
    private ManagerRepository managerRepository;
    private EntityManager entityManager ;

    public ManagerService(ManagerRepository managerRepository, EntityManager entityManager){
        this.managerRepository=managerRepository;
        this.entityManager=entityManager;
    }

    public ManagerEntity save(ManagerEntity managerEntity){
        return managerRepository.save(managerEntity);
    }

    public void delete(Long id){
         managerRepository.deleteById(id);
    }

    public ManagerEntity update(ManagerEntity managerEntity){
        return managerRepository.save(managerEntity);
    }

    public List<ManagerEntity> FindAll(){
        return managerRepository.findAll();
    }

    public ManagerDTO findById(Long id){
        ManagerDTO managerDTO = CustomerMapperManager.convertToManagerDTO(managerRepository.findById(id).get());
        return managerDTO;
    }

    public List<ManagerEntity> findManagersInDepartments() {
        JPAQuery<ManagerEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity manager = QManagerEntity.managerEntity;
        QDepartmentEntity department = QDepartmentEntity.departmentEntity;

        return query.select(manager)
                .from(manager)
                .leftJoin(manager.department_id, department)
                .fetchJoin()
                .fetch();
    }
    public List<ManagerEntity> findManagersInDepartmentById(Long departmentId) {
        JPAQuery<ManagerEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity manager = QManagerEntity.managerEntity;
        QDepartmentEntity department = QDepartmentEntity.departmentEntity;

        return query.select(manager)
                .from(manager)
                .leftJoin(manager.department_id, department)
                .where(department.id.eq(departmentId))
                .fetchJoin()
                .fetch();
    }
}
