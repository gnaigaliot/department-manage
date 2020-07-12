/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.department.personManager.form;

import java.util.Date;
import java.util.List;

import com.example.common.CommonUtil;

/**
 * PersonForm class
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class PersonForm {

    private Long    id;
    private String     code;
    private String     name;
    private Long       gender;
    private String     address;
    private String     identityNumber;
    private Date       dateOfBirth;
    private String     phoneNumber;
    private String     email;
	private String     lstApartmentId;


	
    public String getLstApartmentId() throws Exception {
    	if(CommonUtil.isNullOrEmpty(lstApartmentId)) {
    		return null;
    	}
    	else {
    		return lstApartmentId;
    	}
	}

	public void setLstApartmentId(String lstApartmentId) {
		this.lstApartmentId = lstApartmentId;
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
     * Set the "name" field value
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the "name" field value
     * @return the field value
     */
    public String getName() {
        return this.name;
    }


    /**
     * Set the "gender" field value
     * @param gender
     */
    public void setGender(Long gender) {
        this.gender = gender;
    }

    /**
     * Get the "gender" field value
     * @return the field value
     */
    public Long getGender() {
        return this.gender;
    }


    /**
     * Set the "address" field value
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the "address" field value
     * @return the field value
     */
    public String getAddress() {
        return this.address;
    }


    /**
     * Set the "identityNumber" field value
     * @param identityNumber
     */
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    /**
     * Get the "identityNumber" field value
     * @return the field value
     */
    public String getIdentityNumber() {
        return this.identityNumber;
    }


    /**
     * Set the "dateOfBirth" field value
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get the "dateOfBirth" field value
     * @return the field value
     */
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }


    /**
     * Set the "phoneNumber" field value
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the "phoneNumber" field value
     * @return the field value
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    /**
     * Set the "email" field value
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the "email" field value
     * @return the field value
     */
    public String getEmail() {
        return this.email;
    }

}
