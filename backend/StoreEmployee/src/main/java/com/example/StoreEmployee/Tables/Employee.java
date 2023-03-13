package com.example.StoreEmployee.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id; 
    private String name; 
    private Integer age; 
    private Integer store_id; 
    private String skill; 
    private LocalDate created_date; 
    private String status;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    /**
     * @return the store_id
     */
    public Integer getStore_id() {
        return store_id;
    }
    /**
     * @param store_id the store_id to set
     */
    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }
    /**
     * @return the skill
     */
    public String getSkill() {
        return skill;
    }
    /**
     * @param skill the skill to set
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }
    /**
     * @return the created_date
     */
    public LocalDate getCreated_date() {
        return created_date;
    }
    /**
     * @param created_date the created_date to set
     */
    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    } 
    
}
