package com.example.bill.billServiceType.bean;

import java.util.Date;

public class BillWaterElectricBean {
	private Long id;
	private String codeBill;
	private Double oldNumber;
	private Double newNumber;
	private Double amount;
	private Date formDate;
	private Date toDate;
	private Long status;
	private Date createdDate;
	private Date editedDate;
	private Long idApartment;
	private Double totalPrice;
	private Long idBillType;
	private String codeApartment;
	private String personName;
	private String numberPhone;
	private Long type;
	
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getNumberPhone() {
		return numberPhone;
	}
	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeBill() {
		return codeBill;
	}
	public void setCodeBill(String codeBill) {
		this.codeBill = codeBill;
	}
	public Double getOldNumber() {
		return oldNumber;
	}
	public void setOldNumber(Double oldNumber) {
		this.oldNumber = oldNumber;
	}
	public Double getNewNumber() {
		return newNumber;
	}
	public void setNewNumber(Double newNumber) {
		this.newNumber = newNumber;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getFormDate() {
		return formDate;
	}
	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
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
	public Long getIdApartment() {
		return idApartment;
	}
	public void setIdApartment(Long idApartment) {
		this.idApartment = idApartment;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getIdBillType() {
		return idBillType;
	}
	public void setIdBillType(Long idBillType) {
		this.idBillType = idBillType;
	}
	public String getCodeApartment() {
		return codeApartment;
	}
	public void setCodeApartment(String codeApartment) {
		this.codeApartment = codeApartment;
	}
	
	
}
