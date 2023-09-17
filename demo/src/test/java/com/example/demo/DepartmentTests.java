package com.example.demo;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    public DepartmentTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testSaveDepartment() throws Exception {
        // Create a sample DepartmentEntity object
        DepartmentEntity department = new DepartmentEntity();
        department.setId(1L);
        department.setName("HR Department");
        when(departmentService.save(any(DepartmentEntity.class))).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR Department\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("HR Department"));
    }
}
