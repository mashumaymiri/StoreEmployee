package com.example.StoreEmployee;

import com.example.StoreEmployee.Tables.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import java.util.List;
import java.util.ArrayList;


@Controller
public class WebController {
	@Autowired 
	private StoreRepository storeRepository;
	@Autowired 
	private EmployeeRepository employeeRepository;


	@GetMapping("/custore")
	public String custore(Model model) {
		model.addAttribute("store", new Store());
		model.addAttribute("stores", storeRepository.findAll());
		return "custore";
	}
	
	@PostMapping("/custore/createStore")
	public String custoreCreate(@ModelAttribute Store store, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);	

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("name", store.getName());
		map.add("address", store.getAddress());
		map.add("store_type", store.getStore_type());
		map.add("status", store.getStatus());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(
		"http://localhost:8080/storeapplication/stores", request , String.class);

		model.addAttribute("store", new Store());
		model.addAttribute("stores", storeRepository.findAll());
		return "custore";
	}
	
	@PostMapping("/custore/updateStore")
	public String custoreUpdate(@ModelAttribute Store store, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);	

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("name", store.getName());
		map.add("address", store.getAddress());
		map.add("store_type", store.getStore_type());
		map.add("status", store.getStatus());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		restTemplate.put(
		"http://localhost:8080/storeapplication/stores/"+store.getId(), request , String.class);

		model.addAttribute("store", new Store());
		model.addAttribute("stores", storeRepository.findAll());
		return "custore";
	}

	@GetMapping("/cuemployee")
	public String cuemployee(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("stores", storeRepository.findAll());
		return "cuemployee";
	}
	
	@PostMapping("/cuemployee/createEmployee")
	public String cuemployeeCreate(@ModelAttribute Employee employee, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);	

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("name", employee.getName());
		map.add("age", Integer.toString(employee.getAge()));
		map.add("storeid",Integer.toString(employee.getStoreid()));
		map.add("skill", employee.getSkill());
		map.add("status", employee.getStatus());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(
		"http://localhost:8080/storeapplication/employees", request , String.class);

		model.addAttribute("employee", new Employee());
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("stores", storeRepository.findAll());
		return "cuemployee";
	}
	
	@PostMapping("/cuemployee/updateEmployee")
	public String cuemployeeUpdate(@ModelAttribute Employee employee, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);	

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("name", employee.getName());
		map.add("age", Integer.toString(employee.getAge()));
		map.add("storeid",Integer.toString(employee.getStoreid()));
		map.add("skill", employee.getSkill());
		map.add("status", employee.getStatus());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		restTemplate.put(
		"http://localhost:8080/storeapplication/employees/"+employee.getId(), request , String.class);

		model.addAttribute("employee", new Employee());
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("stores", storeRepository.findAll());
		return "cuemployee";
	}
	
	@GetMapping("/demployee")
	public String demployee(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("employees", employeeRepository.findAll());
		return "demployee";
	}

	@PostMapping("/demployee/deleteEmployee")
	public String demployeeDelete(@ModelAttribute Employee employee, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		restTemplate.delete(
		"http://localhost:8080/storeapplication/employees/"+employee.getId(), request , String.class);

		model.addAttribute("employee", new Employee());
		model.addAttribute("employees", employeeRepository.findAll());
		return "demployee";
	}
	
	@GetMapping("/dstore")
	public String dstore(Model model) {
		model.addAttribute("store", new Store());
		model.addAttribute("stores", storeRepository.findAll());
		return "dstore";
	}

	@PostMapping("/dstore/deleteStore")
	public String dstoreDelete(@ModelAttribute Employee store, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		restTemplate.delete(
		"http://localhost:8080/storeapplication/stores/"+store.getId(), request , String.class);

		model.addAttribute("store", new Store());
		model.addAttribute("stores", storeRepository.findAll());
		return "dstore";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("activeStores", storeRepository.findByStatus("Active").size());
		model.addAttribute("activeEmployees", employeeRepository.findByStatus("Active").size());
		MultiValueMap<String, Integer> empInEachStore= new LinkedMultiValueMap<>();
		
		for (Store i:  storeRepository.findAll()) {
			empInEachStore.add(Integer.toString(i.getId()),  employeeRepository.findByStoreid(i.getId()).size());
		}
		System.out.println(empInEachStore);

		model.addAttribute("stores", empInEachStore);
		return "dashboard";
	}
}