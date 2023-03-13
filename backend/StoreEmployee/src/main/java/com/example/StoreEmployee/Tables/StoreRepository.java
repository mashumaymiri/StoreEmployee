package com.example.StoreEmployee.Tables;

import org.springframework.data.repository.CrudRepository;

import com.example.StoreEmployee.Tables.Store;

// This will be AUTO IMPLEMENTED by Spring into a Bean called StoreRepository
// CRUD refers Create, Read, Update, Delete

public interface StoreRepository extends CrudRepository<Store, Integer> {

}