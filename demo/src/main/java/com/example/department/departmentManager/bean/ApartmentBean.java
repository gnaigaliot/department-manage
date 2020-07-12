/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.department.departmentManager.bean;

import java.util.Date;

/**
 * ApartmentBean for table "apartment"
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class ApartmentBean {

    private Long    id;
    private String     code;
    private Double price;
    private Long area;
    private String     description;
    private Byte       status;
    private Date       createdDate;
    private Date       editedDate;
    private String     createdBy;
    private String     editedBy;
    private String name;
    private Long amountPersonOfApart;
    


    public Long getAmountPersonOfApart() {
		return amountPersonOfApart;
	}

	public void setAmountPersonOfApart(Long amount) {
		amountPersonOfApart = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * Set the "id" field value
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the "id" field value
     * @return the field value
     */
    public Long getId() {
        return this.id;
    }


    /**
     * Set the "code" field value
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the "code" field value
     * @return the field value
     */
    public String getCode() {
        return this.code;
    }


    /**
     * Set the "price" field value
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Get the "price" field value
     * @return the field value
     */
    public Double getPrice() {
        return this.price;
    }


    /**
     * Set the "area" field value
     * @param area
     */
    public void setArea(Long area) {
        this.area = area;
    }

    /**
     * Get the "area" field value
     * @return the field value
     */
    public Long getArea() {
        return this.area;
    }


    /**
     * Set the "description" field value
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the "description" field value
     * @return the field value
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Set the "status" field value
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * Get the "status" field value
     * @return the field value
     */
    public Byte getStatus() {
        return this.status;
    }


    /**
     * Set the "createdDate" field value
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get the "createdDate" field value
     * @return the field value
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }


    /**
     * Set the "editedDate" field value
     * @param editedDate
     */
    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    /**
     * Get the "editedDate" field value
     * @return the field value
     */
    public Date getEditedDate() {
        return this.editedDate;
    }


    /**
     * Set the "createdBy" field value
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the "createdBy" field value
     * @return the field value
     */
    public String getCreatedBy() {
        return this.createdBy;
    }


    /**
     * Set the "editedBy" field value
     * @param editedBy
     */
    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    /**
     * Get the "editedBy" field value
     * @return the field value
     */
    public String getEditedBy() {
        return this.editedBy;
    }

}
