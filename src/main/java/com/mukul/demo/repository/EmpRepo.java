package com.mukul.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.mukul.demo.entity.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer>{

	@Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:keyword,'%') OR e.phno LIKE CONCAT('%',:keyword,'%') OR e.email LIKE CONCAT('%',:keyword,'%') OR e.salary LIKE CONCAT('%',:keyword,'%') OR e.id LIKE CONCAT('%',:keyword,'%') OR e.address LIKE CONCAT('%',:keyword,'%')")
	public List<Employee> searchEmp(String keyword);
	
	@Query(value = "SELECT * FROM emp_system e WHERE e.name LIKE CONCAT('%',:keyword,'%') OR e.phno LIKE CONCAT('%',:keyword,'%')",nativeQuery = true)
	public List<Employee> searchEmpSql(String keyword);
	
//	@Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:searchTerm,'%') OR e.phno LIKE CONCAT('%',:searchTerm,'%') OR e.email LIKE CONCAT('%',:searchTerm,'%') OR e.salary LIKE CONCAT('%',:searchTerm,'%') OR e.id LIKE CONCAT('%',:searchTerm,'%') OR e.address LIKE CONCAT('%',:searchTerm,'%')")
// ArrayList<Employee> findByNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);
	
	@Query("SELECT e.name FROM Employee e WHERE e.name LIKE CONCAT('%',:keyword,'%') OR e.phno LIKE CONCAT('%',:keyword,'%') OR e.email LIKE CONCAT('%',:keyword,'%') OR e.salary LIKE CONCAT('%',:keyword,'%') OR e.id LIKE CONCAT('%',:keyword,'%') OR e.address LIKE CONCAT('%',:keyword,'%')")
    List<String> findByNameContainingIgnoreCase(@Param("keyword") String keyword);
	

	//SELECT e.name FROM Employee e WHERE e.name LIKE %:keyword%
	
	
//	//@Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:keyword,'%') OR e.phno LIKE CONCAT('%',:keyword,'%') OR e.email LIKE CONCAT('%',:keyword,'%') OR e.salary LIKE CONCAT('%',:keyword,'%') OR e.id LIKE CONCAT('%',:keyword,'%') OR e.address LIKE CONCAT('%',:keyword,'%')")
//	@Query("from Employee as e where e.id =:userId")
//	public Page<Employee> findContactsByUser(@Param("userId") int userId, Pageable pageable);
	


	@Query("SELECT e FROM Employee e WHERE lower(e.name) LIKE lower(concat('%', :keyword, '%')) OR lower(e.address) LIKE lower(concat('%', :keyword, '%')) OR lower(e.email) LIKE lower(concat('%', :keyword, '%')) ORDER BY "
	        + "CASE WHEN :sortBy = 'name' THEN e.name END ASC, "
	        + "CASE WHEN :sortBy = 'address' THEN e.address END ASC, "
	        + "CASE WHEN :sortBy = 'email' THEN e.email END ASC, "
	        + "CASE WHEN :sortBy = 'salary' THEN e.salary END ASC")
	Page<Employee> searchEmpPageable(@Param("keyword") String keyword, @Param("sortBy") String sortBy, Pageable pageable);


	

	


	@Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:keyword,'%')")
	Page<Employee> searchEmpByNamePageable(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT e FROM Employee e WHERE e.address LIKE CONCAT('%',:keyword,'%')")
	Page<Employee> searchEmpByAddressPageable(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT e FROM Employee e WHERE e.email LIKE CONCAT('%',:keyword,'%')")
	Page<Employee> searchEmpByEmailPageable(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT e FROM Employee e WHERE e.salary LIKE CONCAT('%',:keyword,'%')")
	Page<Employee> searchEmpBySalaryPageable(@Param("keyword") String keyword, Pageable pageable);


	Optional<Employee> findByEmail(String email);
	
}
