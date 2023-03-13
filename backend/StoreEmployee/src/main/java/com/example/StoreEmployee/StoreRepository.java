package com.example.StoreEmployee;

import org.springframework.data.repository.CrudRepository;

import com.example.StoreEmployee.Store;

// This will be AUTO IMPLEMENTED by Spring into a Bean called StoreRepository
// CRUD refers Create, Read, Update, Delete

public interface StoreRepository extends CrudRepository<Store, Integer> {

}