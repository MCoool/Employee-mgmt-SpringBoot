

package com.mukul.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.mukul.demo.EmpSystemApplication;
import com.mukul.demo.entity.Employee;

@DataJpaTest
//@ContextConfiguration(classes = EmpRepo.class)
//@ComponentScan(basePackages = "com.mukul.demo.repository")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableJpaRepositories
public class EmpRepoTest {

    @Autowired
    private EmpRepo repo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void addEmpTest() {
        Employee e = Employee.builder()
        		     //.id(1)
        		     .name("Sachin")
        		     .address("Dadar")
        		     .email("Sachin.tendulkar@gmail.com")
        		     .phno("9558788822")
        		     .salary(35000000)
        		     .build();
//        		new Employee(1, "Sachin", "Dadar", "Sachin.tendulkar@gmail.com", "9558794522", 35000000);
        repo.save(e);
        System.out.println(e);
        assertThat(e.getId()).isGreaterThan(0);
    }
    
    
    
    
    @Test
    @Order(2)
    public void getEmployeeTest() {
    	Employee e = repo.findById(1).get();
    	assertThat(e.getId()).isEqualTo(1);
  
    }
    
    
//    @Test
//    public void searchEmpTest() {
//    	Employee e = repo.searchEmp("ron").get(0);
//    	assertThat(e.getName()).isEqualTo("ron");
//    }
    
    
    
    
    @Test
    @Order(3)
    public void getListOfEmployeeTest() {
    	List<Employee> e = repo.findAll();
    	
    	assertThat(e.size()).isGreaterThan(0);
    }
    
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateListOfEmployeeTest() {
    	Employee e = repo.findById(1).get();
    	 e.setEmail("ram@email.com");
    	 Employee employeeUpdate = repo.save(e);
    	assertThat(employeeUpdate.getEmail()).isEqualTo("ram@email.com");
    }
    
    
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmpTest() {
    	Employee e = repo.findById(1).get();
    	
    	repo.delete(e);
    	
    	Employee e1 = null;
    	Optional<Employee> optionalEmployee = repo.findByEmail("ram@email.com");
    	
    	
    	if(optionalEmployee.isPresent()) {
    		e1 = optionalEmployee.get();
    	}
    	
    	Assertions.assertThat(e1).isNull();
    	
    }
    
    
    
}









//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import com.mukul.demo.entity.Employee;
//import com.mukul.demo.repository.EmpRepo;
//import com.mukul.demo.service.EmpService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class EmpRepoTest {
//
//    @Mock
//    private EmpRepo empRepo;
//
//    @InjectMocks
//    private EmpService empService;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testSearchEmp() {
//        // Arrange
//        String keyword = "John";
//        List<Employee> expectedEmployees = Arrays.asList(new Employee(1, "John Doe", "Address 1", "john@example.com", "1234567890", 5000));
//
//        Mockito.when(empRepo.searchEmp(Mockito.anyString())).thenReturn(expectedEmployees);
//
//        // Act
//        List<Employee> result = empService.getAllEmp(keyword);
//
//        // Assert
//        assertEquals(expectedEmployees, result);
//    }
//
//    @Test
//    public void testSearchEmpSql() {
//        // Arrange
//        String keyword = "John";
//        List<Employee> expectedEmployees = Arrays.asList(new Employee(1, "John Doe", "Address 1", "john@example.com", "1234567890", 5000));
//
//        Mockito.when(empRepo.searchEmpSql(Mockito.anyString())).thenReturn(expectedEmployees);
//
//        // Act
//        List<Employee> result = empService.getAllEmp(keyword);
//
//        // Assert
//        assertEquals(expectedEmployees, result);
//    }
//
//    @Test
//    public void testFindByNameContainingIgnoreCase() {
//        // Arrange
//        String keyword = "John";
//        List<String> expectedNames = Arrays.asList("John Doe", "Johnny");
//
//        Mockito.when(empRepo.findByNameContainingIgnoreCase(Mockito.anyString())).thenReturn(expectedNames);
//
//        // Act
//        List<String> result = empService.getAutocompleteSuggestions(keyword);
//
//        // Assert
//        assertEquals(expectedNames, result);
//    }
//
//    @Test
//    public void testSearchEmpPageable() {
//        // Arrange
//        String keyword = "John";
//        String sortBy = "name";
//        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
//        Pageable pageable = PageRequest.of(0, 10, sort);
//        List<Employee> expectedEmployees = Arrays.asList(new Employee(1, "John Doe", "Address 1", "john@example.com", "1234567890", 5000));
//        Page<Employee> expectedPage = new PageImpl<>(expectedEmployees, pageable, expectedEmployees.size());
//
//        Mockito.when(empRepo.searchEmpPageable(Mockito.anyString(), Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(expectedPage);
//
//        // Act
//        Page<Employee> result = empService.getAllEmp(keyword, 1, 10, sortBy, "asc");
//
//        // Assert
//        assertEquals(expectedPage, result);
//        assertEquals(expectedEmployees, result.getContent());
//    }
//
//    @Test
//    public void testSaveEmployee() {
//        // Arrange
//        Employee employee = new Employee(1, "John Doe", "Address 1", "john@example.com", "1234567890", 5000);
//
//        Mockito.doNothing().when(empRepo).save(Mockito.any(Employee.class));
//
//        // Act
//        empService.addEmp(employee);
//
//        // Assert
//        Mockito.verify(empRepo, Mockito.times(1)).save(employee);
//    }
//
//
//    @Test
//    public void testGetEmployeeById() {
//        // Arrange
//        int employeeId = 1;
//        Employee expectedEmployee = new Employee(1, "John Doe", "Address 1", "john@example.com", "1234567890", 5000);
//
//        Mockito.when(empRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(expectedEmployee));
//
//        // Act
//        Employee result = empService.getEmpById(employeeId);
//
//        // Assert
//        assertEquals(expectedEmployee, result);
//    }
//
//    @Test
//    public void testDeleteEmployee() {
//        // Arrange
//        int employeeId = 1;
//
//        // Act
//        empService.deleteEmp(employeeId);
//
//        // Assert
//        Mockito.verify(empRepo, Mockito.times(1)).deleteById(employeeId);
//    }
//
//}



//
//import com.mukul.demo.entity.Employee;
//import com.mukul.demo.repository.EmpRepo;
//import com.mukul.demo.service.EmpService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@DataJpaTest
//public class EmpRepoTest {
//
//    @Mock
//    private EmpRepo empRepo;
//
//    private EmpService empService;
//
//    @BeforeEach
//    public void setUp() {
//        // Initialize mock objects
//        MockitoAnnotations.openMocks(this);
//
//        // Create an instance of EmpService with the mock repository
//        empService = new EmpService(empRepo);
//    }
//
//    @Test
//    public void testSaveEmployee() {
//        // Create an employee object
//        Employee employee = new Employee();
//        employee.setName("John Doe");
//        employee.setAddress("123 Main St");
//        employee.setEmail("john.doe@example.com");
//        employee.setPhno("1234567890");
//        employee.setSalary(50000);
//
//        // Configure the mock repository
//        when(empRepo.save(any(Employee.class))).thenReturn(employee);
//
//        // Save the employee to the repository
//        Employee savedEmployee = empRepo.save(employee);
//
//        // Verify that the employee is saved successfully and has an ID assigned
//        assertThat(savedEmployee.getId()).isNotNull();
//
//        // Verify that the save method is called on the mock repository
//        verify(empRepo, times(1)).save(employee);
//    }
//
//    @Test
//    public void testFindAllEmployees() {
//        // Create a list of sample employees
//        List<Employee> employees = new ArrayList<>();
//        Employee employee1 = new Employee();
//        employee1.setName("John Doe");
//        employee1.setAddress("123 Main St");
//        employee1.setEmail("john.doe@example.com");
//        employee1.setPhno("1234567890");
//        employee1.setSalary(50000);
//        employees.add(employee1);
//
//        Employee employee2 = new Employee();
//        employee2.setName("Jane Smith");
//        employee2.setAddress("456 Elm St");
//        employee2.setEmail("jane.smith@example.com");
//        employee2.setPhno("9876543210");
//        employee2.setSalary(60000);
//        employees.add(employee2);
//
//        // Configure the mock repository
//        when(empRepo.findAll()).thenReturn(employees);
//
//        // Retrieve all employees from the repository
//        List<Employee> retrievedEmployees = empRepo.findAll();
//
//        // Verify that the correct number of employees are returned
//        assertThat(retrievedEmployees).hasSize(2);
//
//        // Verify that the findAll method is called on the mock repository
//        verify(empRepo, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindEmployeeById() {
//        // Create an employee object
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setName("John Doe");
//        employee.setAddress("123 Main St");
//        employee.setEmail("john.doe@example.com");
//        employee.setPhno("1234567890");
//        employee.setSalary(50000);
//
//        // Configure the mock repository
//        when(empRepo.findById(eq(1))).thenReturn(Optional.of(employee));
//
//        // Find the employee by ID
//        Optional<Employee> optionalEmployee = empRepo.findById(1);
//
//        // Verify that the employee is found and has the correct ID
//        assertThat(optionalEmployee).isPresent();
//        assertThat(optionalEmployee.get().getId()).isEqualTo(1);
//
//        // Verify that the findById method is called on the mock repository
//        verify(empRepo, times(1)).findById(1);
//    }
//
////    @Test
////    public void testDeleteEmployee() {
////        // Create an employee object
////        Employee employee = new Employee();
////        employee.setId(1);
////        employee.setName("John Doe");
////        employee.setAddress("123 Main St");
////        employee.setEmail("john.doe@example.com");
////        employee.setPhno("1234567890");
////        employee.setSalary(50000);
////
////        // Configure mock repository
////        when(empRepo.findById(1)).thenReturn(Optional.of(employee));
////
////        // Delete the employee
////        empService.deleteEmp(1);
////
////        // Verify that the employee was deleted
////        verify(empRepo, times(1)).deleteById(1);
////
////        // Try to find the deleted employee by ID
////        Optional<Employee> optionalEmployee = empRepo.findById(1);
////
////        // Verify that the employee is not found
////        assertThat(optionalEmployee).isEmpty();
////    }
//}
//
//































