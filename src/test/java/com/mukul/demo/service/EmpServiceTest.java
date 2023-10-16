package com.mukul.demo.service;


//Java Program to Illustrate Unit Testing of Service ClassI



//import static org.mockito.Mockito.verify;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.mukul.demo.repository.EmpRepo;
//
//@ExtendWith(MockitoExtension.class)
//
////Main class
//
//public class EmpServiceTest {
//	@Mock private EmpRepo repo;
//
//	private EmpService service;
//
//	@BeforeEach void setUp()
//	{
//		this.service
//			= new EmpService(this.repo);
//	}
//
//	@Test void getAllPerson()
//	{
//		service.getAllEmp(null);
//		verify(repo).findAll();
//	}
//}


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.mukul.demo.entity.Employee;
import com.mukul.demo.repository.EmpRepo;
 
class EmpServiceTest {

    private EmpService empService;
    private EmpRepo empRepo;

    @BeforeEach
    void setUp() {
        empRepo = mock(EmpRepo.class);
        empService = new EmpService(empRepo);
    }

    @Test
    void testGetAllEmpWithKeyword() {
        String keyword = "test";
        int pageNo = 1;
        int pageSize = 10;
        String sortBy = "name";
        String sortOrder = "asc";

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(Employee.builder()
   		     .id(1)
   		     .name("Sachin")
   		     .address("Dadar")
   		     .email("Sachin.tendulkar@gmail.com")
   		     .phno("9558794522")
   		     .salary(35000000)
   		     .build());

        Page<Employee> expectedPage = new PageImpl<>(expectedEmployees);

        when(empRepo.searchEmpPageable(keyword, sortBy, PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.ASC, sortBy))))
                .thenReturn(expectedPage);

        Page<Employee> result = empService.getAllEmp(keyword, pageNo, pageSize, sortBy, sortOrder);

        assertEquals(expectedPage, result);
        verify(empRepo).searchEmpPageable(keyword, sortBy, PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.ASC, sortBy)));
    }

    @Test
    void testGetAllEmpWithoutKeyword() {
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(Employee.builder()
      		     .id(1)
       		     .name("Sachin")
       		     .address("Dadar")
       		     .email("Sachin.tendulkar@gmail.com")
       		     .phno("9558794522")
       		     .salary(35000000)
       		     .build());

        when(empRepo.findAll()).thenReturn(expectedEmployees);

        List<Employee> result = empService.getAllEmp(null);

        assertEquals(expectedEmployees, result);
        verify(empRepo).findAll();
    }

    // Add more test methods for other methods in EmpService class

    @Test
    void testGetEmpByIdWithValidId() {
        int id = 1;
        Employee expectedEmployee = Employee.builder()
      		     .id(1)
       		     .name("Sachin")
       		     .address("Dadar")
       		     .email("Sachin.tendulkar@gmail.com")
       		     .phno("9558794522")
       		     .salary(35000000)
       		     .build();

        when(empRepo.findById(id)).thenReturn(Optional.of(expectedEmployee));

        Employee result = empService.getEmpById(id);

        assertEquals(expectedEmployee, result);
        verify(empRepo).findById(id);
    }

    @Test
    void testGetEmpByIdWithInvalidId() {
        int id = 1;

        when(empRepo.findById(id)).thenReturn(Optional.empty());

        Employee result = empService.getEmpById(id);

        assertNull(result);
        verify(empRepo).findById(id);
    }

    // Add more test methods to cover other scenarios

    // Test methods for other methods in EmpService class
}



