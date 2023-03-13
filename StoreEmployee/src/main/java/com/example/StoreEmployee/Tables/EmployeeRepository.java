package com.example.StoreEmployee.Tables;

import org.springframework.data.repository.CrudRepository;

import com.example.StoreEmployee.Tables.Employee;

// This will be AUTO IMPLEMENTED by Spring into a Bean called EmployeeRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}