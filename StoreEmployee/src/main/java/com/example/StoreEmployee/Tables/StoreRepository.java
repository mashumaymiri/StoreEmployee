package com.example.StoreEmployee.Tables;

import org.springframework.data.repository.CrudRepository;

import com.example.StoreEmployee.Tables.Store;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called StoreRepository
// CRUD refers Create, Read, Update, Delete

public interface StoreRepository extends CrudRepository<Store, Integer> {
    List<Store> findByStatus(String status);
}