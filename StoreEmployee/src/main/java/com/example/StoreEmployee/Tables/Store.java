package com.example.StoreEmployee.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Console;
import java.time.LocalDate;

@Entity
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String address;
  private String store_type;
  private String status;
  private LocalDate created_date;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getStore_type() {
    return store_type;
  }

  public void setStore_type(String store_type) {
    this.store_type = store_type;
  }

  public String getStatus() {
    return status;
  }

  public Boolean setStatus(String status) {
    if (status.equals("Active") || status.equals("Inactive")) {
      this.status = status;
      return true;
    } else {
      return false;
    }
  }

  public LocalDate getCreated_date() {
    return created_date;
  }

  public void setCreated_date(LocalDate created_date) {
    this.created_date = created_date;
  }
}