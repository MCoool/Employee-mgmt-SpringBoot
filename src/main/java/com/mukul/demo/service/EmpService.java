package com.mukul.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mukul.demo.entity.*;
import com.mukul.demo.repository.*;

@Service
public class EmpService {
	
	@Autowired
	private final EmpRepo repo;
	
	 @Autowired
	   public EmpService(EmpRepo repo) {
	        this.repo = repo;
	    }
	 
//	 public ArrayList<Employee> getAutocompleteSuggestions(String searchTerm) {
//	        return repo.findByNameContainingIgnoreCase(searchTerm);
//	    }
//	 
//	 public Page<Employee> getAllEmp(String keyword, int pageNo, int pageSize) {
//		    Pageable pageable;
//		    if (pageNo <= 0) {
//		        pageable = PageRequest.of(0, pageSize);
//		    } else {
//		        pageable = PageRequest.of(pageNo - 1, pageSize);
//		    }
//		    if (keyword != null) {
//		        return repo.searchEmpPageable(keyword, pageable);
//		    }
//		    return repo.findAll(pageable);
//		}

	 
//		public Page<Employee> getAllEmp(String keyword, int pageNo, int pageSize, Sort sort) {
//			Pageable pageable;
//			if (pageNo <= 0) {
//				pageable = PageRequest.of(0, pageSize, sort);
//			} else {
//				pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//			}
//			if (keyword != null) {
//				return repo.searchEmpPageable(keyword, pageable);
//			}
//			return repo.findAll(pageable);
//		}
//	 
	 
	 
	 
	 

//public Page<Employee> getAllEmp(String keyword, int pageNo, int pageSize, String sortBy) {
//    Pageable pageable;
//    if (pageNo <= 0) {
//        pageable = PageRequest.of(0, pageSize, Sort.by(sortBy));
//    } else {
//        pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
//    }
//    if (keyword != null) {
//        return repo.searchEmpPageable(keyword, pageable);
//    }
//    return repo.findAll(pageable);
//}

//	 public Page<Employee> getAllEmp(String keyword, int pageNo, int pageSize, String sortBy, String sortOrder, boolean sortByInteger) {
//		    Pageable pageable;
//
//		    if (sortByInteger && sortBy.equals("salary")) {
//		        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC, "salary");
//		        pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//		    } else {
//		        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC,"salary");
//		        pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//		    }
//
//		    if (keyword != null) {
//		        return repo.searchEmpPageable(keyword.toLowerCase(), pageable);
//		    }
//
//		    return repo.findAll(pageable);
//		}
	 
	 
	 
	 
	 public Page<Employee> getAllEmp(String keyword, int pageNo, int pageSize, String sortBy, String sortOrder) {
		    Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		    Sort sort = Sort.by(direction, sortBy);
		    
		    int pageIndex = pageNo > 0 ? pageNo - 1 : 0; // Ensure non-negative page index
		    Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

		    if (keyword != null) {
		        return repo.searchEmpPageable(keyword, sortBy, pageable);
		    }

		    return repo.findAll(pageable);
		}



	 public List<String> getAutocompleteSuggestions(String keyword) {
	        return repo.findByNameContainingIgnoreCase(keyword);
	    }
	
	public void addEmp(Employee e) {
		repo.save(e);
	}
	
	public List<Employee> getAllEmp(String keyword){
		if(keyword != null) {
			return repo.searchEmp(keyword);
		}
		
		return repo.findAll();
	}
	
//	public Page<Employee> getEmpId(int id, Pageable pageable){
//		Optional<Employee> e= repo.findById(id);
//		if(e.isPresent()) {
//			return (Page<Employee>) e.get();
//		}
//		return null;
//		
//	}
	
	
	
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1,pageSize);
		
		return this.repo.findAll(pageable);
	}

	public Employee getEmpById(int id) {
		Optional<Employee> e= repo.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	
//	public Employee getId(int page) {
//		Optional<Employee> e= repo.findById(page);
//		if(e.isPresent()) {
//			return e.get();
//		}
//		return null;
//	}
	
	public void deleteEmp(int id) {
		repo.deleteById(id);
	}


}
