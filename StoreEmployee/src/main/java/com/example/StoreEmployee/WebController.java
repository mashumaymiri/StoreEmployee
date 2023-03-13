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
		map.add("store_id",Integer.toString(employee.getStore_id()));
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
		map.add("store_id",Integer.toString(employee.getStore_id()));
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

}