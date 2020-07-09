package com.example.bill.billServiceType.bean;

import java.util.Date;

public class BillOrtherBean {
	
	private Long id;
	private String code;
	private Date fromDate ; 
	private Date toDate ;
	private String status ;
	private Long month;
	private Long year;
	private Date createdDate ;
	private Date editedDate;
	private String createdBy;
	private String editedBy;
	private Long idDepartment;
	private String codeApartment;
    private String name;
    private String numberPhone;
    private Long totalPrice;
    
    
	
    
    
    public String getNumberPhone() {
        return numberPhone;
    }


    
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }


    
    public Long getTotalPrice() {
        return totalPrice;
    }


    
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
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
    
    public Date getFromDate() {
        return fromDate;
    }
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    
    public Date getToDate() {
        return toDate;
    }
    
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getMonth() {
        return month;
    }
    
    public void setMonth(Long month) {
        this.month = month;
    }
    
    public Long getYear() {
        return year;
    }
    
    public void setYear(Long year) {
        this.year = year;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Date getEditedDate() {
        return editedDate;
    }
    
    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getEditedBy() {
        return editedBy;
    }
    
    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }
    
    public Long getIdDepartment() {
        return idDepartment;
    }
    
    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    public String getCodeApartment() {
        return codeApartment;
    }
    
    public void setCodeApartment(String codeApartment) {
        this.codeApartment = codeApartment;
    }
	
}
