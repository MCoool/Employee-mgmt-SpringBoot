package com.mukul.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.mukul.demo.entity.*;
import com.mukul.demo.service.EmpService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
//@RequestMapping("/employees")
//@RequestMapping({ "/", "/index" })
public class EmpController {

	@Autowired
	private final EmpService service;

	@Autowired
	public EmpController(EmpService service) {
		this.service = service;
	}

//	     @GetMapping("/autocomplete")
//	     public ArrayList<Employee> autocomplete(@RequestParam("searchTerm") String searchTerm) {
//	         return service.getAutocompleteSuggestions(searchTerm);
//	     }
//	 

	// @GetMapping("/autocomplete")
	@RequestMapping(value = "/autocomplete")
	@ResponseBody
	public List<String> autocomplete(@RequestParam("keyword") String keyword) {

		service.getAutocompleteSuggestions(keyword).stream().forEach(a -> System.out.println(a));
		return service.getAutocompleteSuggestions(keyword);

	}

//	 @RequestMapping(value = "search", method = RequestMethod.GET)
//		@ResponseBody
//		public List<String> search(HttpServletRequest request) {
//			return service.getAutocompleteSuggestions(request.getParameter("searchTerm"));
//		}

// working	     
//	     @GetMapping("/")
//	     public String home(Model model,
//	                        @RequestParam(value = "keyword", required = false) String keyword,
//	                        @RequestParam(defaultValue = "1") int pageNo,
//	                        @RequestParam(defaultValue = "5") int pageSize) {
//
//	         Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize);
//	         List<Employee> employees = page.getContent();
//
//	         model.addAttribute("emp", employees);
//	         model.addAttribute("keyword", keyword);
//	         model.addAttribute("currentPage", pageNo);
//	         model.addAttribute("totalPages", page.getTotalPages());
//
//	         return "index.html";
//	     }

//	WORKINGGBETTER     
//	     @GetMapping("/")
//	     public String home(Model model,
//	                        @RequestParam(value = "keyword", required = false) String keyword,
//	                        @RequestParam(defaultValue = "1") int pageNo,
//	                        @RequestParam(defaultValue = "3") int pageSize) {
//
//	         Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize);
//	         List<Employee> employees = page.getContent();
//
//	         model.addAttribute("emp", employees);
//	         model.addAttribute("keyword", keyword);
//	         model.addAttribute("currentPage", pageNo);
//
//	         int totalPages = page.getTotalPages();
//	         model.addAttribute("totalPages", totalPages);
//
//	         if (pageNo > totalPages) {
//	             // Set pageNo to last page if it exceeds the total pages
//	             pageNo = totalPages;
//	             // Retrieve the page again with the updated pageNo
//	             page = service.getAllEmp(keyword, pageNo, pageSize);
//	             employees = page.getContent();
//	             model.addAttribute("emp", employees);
//	         }
//
//	         return "index.html";
//	     }

//    Beforee Sorting 	     
//	     @GetMapping("/")
//	     public String home(Model model,
//	                        @RequestParam(value = "keyword", required = false) String keyword,
//	                        @RequestParam(defaultValue = "1") int pageNo,
//	                        @RequestParam(defaultValue = "3") int pageSize) {
//
//	         Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize);
//	         List<Employee> employees = page.getContent();
//
//	         model.addAttribute("emp", employees);
//	         model.addAttribute("keyword", keyword);
//	         model.addAttribute("currentPage", pageNo);
//
//	         int totalPages = page.getTotalPages();
//	         model.addAttribute("totalPages", totalPages);
//
//	         // Disable Previous button on the first page
//	         boolean isFirstPage = pageNo == 1;
//	         model.addAttribute("isFirstPage", isFirstPage);
//
//	         // Disable Next button on the last page
//	         boolean isLastPage = pageNo == totalPages;
//	         model.addAttribute("isLastPage", isLastPage);
//
//	         if (pageNo > totalPages) {
//	             // Set pageNo to last page if it exceeds the total pages
//	             pageNo = totalPages;
//	             // Retrieve the page again with the updated pageNo
//	             page = service.getAllEmp(keyword, pageNo, pageSize);
//	             employees = page.getContent();
//	             model.addAttribute("emp", employees);
//	         }
//
//	         return "index.html";
//	     }

//	 	@GetMapping("/")
//		public String home(Model model,
//				@RequestParam(value = "keyword", required = false) String keyword,
//				@RequestParam(defaultValue = "1") int pageNo,
//				@RequestParam(defaultValue = "5") int pageSize) {
//
//			Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize);
//			List<Employee> employees = page.getContent();
//
//			model.addAttribute("emp", employees);
//			model.addAttribute("keyword", keyword);
//			model.addAttribute("currentPage", pageNo);
//			model.addAttribute("totalPages", page.getTotalPages());
//
//			return "index.html";
//		}

//	@GetMapping("/")
//	public String home(Model m,
//			@Param("keyword") String keyword) {
//		
//		
//		List<Employee> e =service.getAllEmp(keyword);
//		m.addAttribute("emp", e);
//		m.addAttribute("keyword",keyword);
//
//		
//	//	return findPaginated(1, m);
//	
//		
//		return "index.html";
//		}
//	
//	@GetMapping("/page/{pageNo}")
//	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model m) {
//		int pageSize = 5;
//		
//		Page<Employee> page = service.findPaginated(pageNo, pageSize);
//		List<Employee> listEmployees = page.getContent();
//		
//		m.addAttribute("currentPage", pageNo);
//		m.addAttribute("totalPages", page.getTotalPages());
//		m.addAttribute("totalItems", page.getTotalElements());
//		m.addAttribute("listEmployee", listEmployees);
//		
//		return "index.html";
//		
//	}

//	@GetMapping("/{page}")
//	public String Pagination(@PathVariable("page") Integer page ,  Model m,
//			@Param("keyword") String keyword) {
//		
//		
//		Pageable pageable  =  PageRequest.of(page, 5);
//		
//		Page<Employee> e =service.getEmpId(page,pageable);
//		m.addAttribute("emp", e);
//		m.addAttribute("keyword",keyword);
//		m.addAttribute("currentPage",page);
//	    m.addAttribute("totalPages",e.getTotalPages());
//	
//		
//		return "index.html";
//		}
//	

//	     @GetMapping("/")
//	     public String home(Model model,
//	                        @RequestParam(value = "keyword", required = false) String keyword,
//	                        @RequestParam(defaultValue = "1") int pageNo,
//	                        @RequestParam(defaultValue = "3") int pageSize,
//	                        @RequestParam(defaultValue = "name") String sortBy,
//	                        @RequestParam(defaultValue = "asc") String sortDirection) {
//
//	         Sort sort = Sort.by(sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
//	         Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize, sort);
//	         List<Employee> employees = page.getContent();
//
//	         model.addAttribute("emp", employees);
//	         model.addAttribute("keyword", keyword);
//	         model.addAttribute("currentPage", pageNo);
//	         model.addAttribute("sortBy", sortBy);
//	         model.addAttribute("sortDirection", sortDirection);
//
//	         int totalPages = page.getTotalPages();
//	         model.addAttribute("totalPages", totalPages);
//
//	         // Disable Previous button on the first page
//	         boolean isFirstPage = pageNo == 1;
//	         model.addAttribute("isFirstPage", isFirstPage);
//
//	         // Disable Next button on the last page
//	         boolean isLastPage = pageNo == totalPages;
//	         model.addAttribute("isLastPage", isLastPage);
//
//	         if (pageNo > totalPages) {
//	             // Set pageNo to last page if it exceeds the total pages
//	             pageNo = totalPages;
//	             // Retrieve the page again with the updated pageNo
//	             page = service.getAllEmp(keyword, pageNo, pageSize, sort);
//	             employees = page.getContent();
//	             model.addAttribute("emp", employees);
//	         }
//
//	         return "index.html";
//	     }

//	     @GetMapping("/")
//	     public String home(Model model,
//	                        @RequestParam(value = "keyword", required = false) String keyword,
//	                        @RequestParam(defaultValue = "1") int pageNo,
//	                        @RequestParam(defaultValue = "3") int pageSize,
//	                        @RequestParam(defaultValue = "name") String sortBy) {
//
//	         Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize, sortBy);
//	         List<Employee> employees = page.getContent();
//
//	         model.addAttribute("emp", employees);
//	         model.addAttribute("keyword", keyword);
//	         model.addAttribute("currentPage", pageNo);
//
//	         int totalPages = page.getTotalPages();
//	         model.addAttribute("totalPages", totalPages);
//
//	         // Disable Previous button on the first page
//	         boolean isFirstPage = pageNo == 1;
//	         model.addAttribute("isFirstPage", isFirstPage);
//
//	         // Disable Next button on the last page
//	         boolean isLastPage = pageNo == totalPages;
//	         model.addAttribute("isLastPage", isLastPage);
//
//	         if (pageNo > totalPages) {
//	             // Set pageNo to last page if it exceeds the total pages
//	             pageNo = totalPages;
//	             // Retrieve the page again with the updated pageNo
//	             page = service.getAllEmp(keyword, pageNo, pageSize, sortBy);
//	             employees = page.getContent();
//	             model.addAttribute("emp", employees);
//	         }
//
//	         return "index.html";
//	     }
//
//	     

//	    

	@GetMapping("/")
	public String home(Model model, @RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder) {

		if (sortBy.equals("name") || sortBy.equals("address") || sortBy.equals("email") || sortBy.equals("salary")) {
			// Update the sortBy value based on the button clicked
			sortBy = sortBy;
		}

//	         if (sortOrder.equals("desc")) {
//	             sortBy += " DESC";
//	         }

		Page<Employee> page = service.getAllEmp(keyword, pageNo, pageSize, sortBy, sortOrder);
		List<Employee> employees = page.getContent();

		model.addAttribute("emp", employees);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("pageSize", pageSize);

		int totalPages = page.getTotalPages();
		model.addAttribute("totalPages", totalPages);

		// Disable Previous button on the first page
		boolean isFirstPage = pageNo == 1;
		model.addAttribute("isFirstPage", isFirstPage);

		// Disable Next button on the last page
		boolean isLastPage = pageNo == totalPages;
		model.addAttribute("isLastPage", isLastPage);

		if (pageNo > totalPages) {
			// Set pageNo to last page if it exceeds the total pages
			pageNo = totalPages;
			// Retrieve the page again with the updated pageNo
			page = service.getAllEmp(keyword, pageNo, pageSize, sortBy, sortOrder);
			employees = page.getContent();
			model.addAttribute("emp", employees);
		}

		// Pass the sorting parameters to the view
		model.addAttribute("sort", sortBy);
		model.addAttribute("sortOrder", sortOrder);

		return "index.html";
	}
	@GetMapping("/loadMore")
	public String loadMoreData(Model model, @RequestParam(defaultValue = "1") int pageNo,
	        @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortOrder) {

	    Page<Employee> page = service.getAllEmp(null, pageNo, pageSize, sortBy, sortOrder);
	    List<Employee> employees = page.getContent();

	    model.addAttribute("emp", employees);

	    return "_employeeList";
	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp.html";

	}

	@PostMapping("/register")
    public String empRegister(@Valid @ModelAttribute Employee e, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "add_emp.html";
        } else {
            service.addEmp(e);
            session.setAttribute("msg", "Employee Added Successfully");
            return "redirect:/";
        }
    }
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
	    Employee e = service.getEmpById(id);
	    m.addAttribute("emp", e);

	    return "edit.html";
	}
	

	@PostMapping("/update")
	public String updateEmp(@Valid @ModelAttribute Employee e, HttpSession session, BindingResult bindingResult) {
//		service.addEmp(e);
//		session.setAttribute("msg", "Emp data update successfully");
//		return "redirect:/";
		
		if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "edit.html";
        } else {
            service.addEmp(e);
            session.setAttribute("msg", "Emp data update successfully");
            return "redirect:/";
        }
		

	}

	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		service.deleteEmp(id);
		session.setAttribute("msg", "Emp data delete successfully");

		return "redirect:/";

	}

}
