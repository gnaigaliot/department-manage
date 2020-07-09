package com.example.employee.bean;

import java.util.Date;

public class EmployeeBean {
    private Long id;
    private String code;
    private String name;
    private Date dateOfBird;
    private Integer gender;
    private String email;
    private String phonenumber;
    private String createdBy;
    private Date createdDate;
    private Integer isWorking;
    // For department
    private Long idDepartment;
    private String departmentName;
    // For position
    private Long idPositions;
    private String positionsName;

    private Long salary;

    // For workprocess
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBird() {
        return dateOfBird;
    }

    public void setDateOfBird(Date dateOfBird) {
        this.dateOfBird = dateOfBird;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Integer isWorking) {
        this.isWorking = isWorking;
    }

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getIdPositions() {
        return idPositions;
    }

    public void setIdPositions(Long idPositions) {
        this.idPositions = idPositions;
    }

    public String getPositionsName() {
        return positionsName;
    }

    public void setPositionsName(String positionsName) {
        this.positionsName = positionsName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
