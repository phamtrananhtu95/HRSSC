package com.example.HRSSC.models;


/**
 * Created by Thien on 6/11/2018.
 */

public class Employee {
    private int id;
    private String name;
    private String skill;
    private String experience;
    private String phone;
    private String email;
    private String company;
    private Integer matched;

    public Employee(){

    }

    public Employee(int id, String name, String skill, String experience, String phone, String email, String company, Integer matched) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.experience = experience;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.matched = matched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getMatched() {
        return matched;
    }

    public void setMatched(Integer matched) {
        this.matched = matched;
    }
}
