package com.example.bill.billServiceType.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_service_type")
public class BillServiceTypeBO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "unit")
	private String unit;

	@Column(name = "price")
	private Double price;

	@Column(name = "status")
	private Long status;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "edited_date")
	private Date editedDate;

	@Column(name = "edited_by")
	private String editeeddBy;

	@Column(name = "created_by")
	private String createddBy;
	
	@Column(name = "is_service")
	private Long isService;
	
	

	public Long getIsService() {
		return isService;
	}

	public void setIsService(Long isService) {
		this.isService = isService;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getEditeeddBy() {
		return editeeddBy;
	}

	public void setEditeeddBy(String editeeddBy) {
		this.editeeddBy = editeeddBy;
	}

	public String getCreateddBy() {
		return createddBy;
	}

	public void setCreateddBy(String createddBy) {
		this.createddBy = createddBy;
	}
	
	
}
