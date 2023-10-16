package com.mukul.demo.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.mukul.demo.controller.EmpController;
import com.mukul.demo.entity.Employee;
import com.mukul.demo.service.EmpService;

public class EmpControllerTest {

    @Mock
    private EmpService empService;

    @InjectMocks
    private EmpController empController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }   

    @Test
    public void testHome() {
        // Mock data
    	 Employee e1 = Employee.builder()
    		     //.id(1)
    		     .name("Sachin")
    		     .address("Dadar")
    		     .email("Sachin.tendulkar@gmail.com")
    		     .phno("9558794522")
    		     .salary(35000000)
    		     .build();
    	 Employee e2 = Employee.builder()
    		     //.id(1)
    		     .name("John")
    		     .address("New York")
    		     .email("John.cena@gmail.com")
    		     .phno("9558798722")
    		     .salary(8000000)
    		     .build();
        List<Employee> employees = Arrays.asList(e1, e2);
        Page<Employee> page = new PageImpl<>(employees);

        // Mock service method
        when(empService.getAllEmp(anyString(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(page);

        // Invoke controller method
        Model model = mock(Model.class);
        String result = empController.home(model,"Sachin", 1, 5, "id", "asc");

        // Verify service method was called
        verify(empService, times(1)).getAllEmp(anyString(), anyInt(), anyInt(), anyString(), anyString());

        // Verify the model attributes
        assertEquals("index.html", result);
        verify(model, times(1)).addAttribute("emp", employees);
    }

    @Test
    public void testAddEmpForm() {
        String result = empController.addEmpForm();
        assertEquals("add_emp.html", result);
    }

    // Add more test cases for other controller methodsPP

}
