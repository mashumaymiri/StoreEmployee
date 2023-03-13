package com.example.StoreEmployee;

import com.example.StoreEmployee.Tables.*;

import java.security.cert.CertPathValidatorException.Reason;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/storeapplication") // This means URL's start with /storeapplication (after Application path)
public class MainController {
  @Autowired // This means to get the bean called Repository
  // Which is auto-generated by Spring, we will use it to handle the data
  private StoreRepository storeRepository;
  @Autowired // This means to get the bean called Repository
  private EmployeeRepository employeeRepository;

  ///////////////////////////////////////////////////////////
  // Store table endpoints

  // Index endpoint
  @GetMapping(path = "/stores")
  public @ResponseBody Iterable<Store> getAllStores() {
    return storeRepository.findAll();
  }

  // show endpoint
  @GetMapping(path = "/stores/{id}")
  public @ResponseBody Store getStore(@PathVariable Integer id) {

    if (storeRepository.existsById(id)) {
      return storeRepository.findById(id).get();
    }

    throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Store Not Found");
  }

  // Create endpoint
  @PostMapping(path = "/stores")
  public @ResponseBody Integer createStore(@RequestParam String name, @RequestParam String address,
      @RequestParam String store_type, @RequestParam String status) {
    Store newStore = new Store();
    newStore.setName(name);
    newStore.setAddress(address);
    newStore.setStore_type(store_type);
    if (!newStore.setStatus(status)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for status. valid values are: Active or Inactive");
    }
    newStore.setCreated_date(LocalDate.now());

    storeRepository.save(newStore);

    return newStore.getId();
  }

  // update endpoint
  @PutMapping(path = "/stores/{id}")
  public @ResponseBody Store updateStore(@PathVariable Integer id, @RequestParam String name,
      @RequestParam String address, @RequestParam String store_type, @RequestParam String status) {
    if (!storeRepository.existsById(id))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found!");

    Store store = storeRepository.findById(id).get();
    store.setName(name);
    store.setAddress(address);
    store.setStore_type(store_type);
    if (!store.setStatus(status)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for status. valid values are: Active or Inactive");
    }
    //store.setCreated_date(LocalDate.now());

    storeRepository.save(store);

    return store;
  }

  // delete endpoint
  @DeleteMapping(path = "/stores/{id}")
  public @ResponseBody String deleteStore(@PathVariable Integer id) {
    if (!storeRepository.existsById(id))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found!");

    storeRepository.deleteById(id);

    return "Deleted";
  }

  // Store table endpoints
  ///////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////
  // Employee table endpoints

  // Index endpoint
  @GetMapping(path = "/employees")
  public @ResponseBody Iterable<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  // show endpoint
  @GetMapping(path = "/employees/{id}")
  public @ResponseBody Employee getEmployee(@PathVariable Integer id) {
    if (employeeRepository.existsById(id)) {
      return employeeRepository.findById(id).get();
    }

    throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Employee Not Found");
  }

  // Create endpoint
  @PostMapping(path = "/employees")
  public @ResponseBody Integer createEmployee(@RequestParam String name, @RequestParam Integer age,
      @RequestParam Integer store_id, @RequestParam String skill, @RequestParam String status) {
    Employee newEmployee = new Employee();
    newEmployee.setName(name);
    newEmployee.setAge(age);

    if (storeRepository.existsById(store_id))
      newEmployee.setStore_id(store_id);
    else
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for store_id. Store does not exist");

    if (!newEmployee.setSkill(skill)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for skill. valid values are: inventory specialist or sales agent");
    }

    if (!newEmployee.setStatus(status)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for status. valid values are: Active or Inactive");
    }
    newEmployee.setCreated_date(LocalDate.now());

    employeeRepository.save(newEmployee);

    return newEmployee.getId();
  }

  // update endpoint
  @PutMapping(path = "/employees/{id}")
  public @ResponseBody Employee updateEmployee(@PathVariable Integer id, @RequestParam String name,
      @RequestParam Integer age, @RequestParam Integer store_id, @RequestParam String skill,
      @RequestParam String status) {

    if (!employeeRepository.existsById(id))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!");

    Employee employee = employeeRepository.findById(id).get();
    employee.setName(name);
    employee.setAge(age);

    if (employeeRepository.existsById(store_id))
      employee.setStore_id(store_id);
    else
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for store_id. Store does not exist");

    if (!employee.setSkill(skill)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for skill. valid values are: inventory specialist or sales agent");
    }

    if (!employee.setStatus(status)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Invalid value for status. valid values are: Active or Inactive");
    }
    //employee.setCreated_date(LocalDate.now());

    employeeRepository.save(employee);

    return employee;
  }

  // delete endpoint
  @DeleteMapping(path = "/employees/{id}")
  public @ResponseBody String deleteEmployee(@PathVariable Integer id) {
    if (!employeeRepository.existsById(id))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!");

    employeeRepository.deleteById(id);

    return "Deleted";
  }

  // Employee table endpoints
  ///////////////////////////////////////////////////////////

}