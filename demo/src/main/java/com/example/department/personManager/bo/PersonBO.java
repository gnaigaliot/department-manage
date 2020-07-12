/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.personManager.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author squadX
 * @since 14/12/2019
 * @version 1.0
 */
@Entity
@Table(name = "person")
public class PersonBO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idPerson;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Long gender;

    @Column(name = "address")
    private String address;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
    
    private Long isMain;

    public Long getIsMain() {
		return isMain;
	}

	public void setIsMain(Long isMain) {
		this.isMain = isMain;
	}

	/**
     * Set the "id" field value
     * @param id
     */
    public void setId( Long id ) {
        this.id = id ;
    }

    /**
     * Get the "id" field value
     * @return the field value
     */
    public Long getId() {
        return this.id;
    }
    
    public Long getIdPerson() {
        return this.id;
    }
    /**
     * Set the "code" field value
     * @param code
     */
    public void setCode( String code ) {
        this.code = code ;
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
    public void setName( String name ) {
        this.name = name ;
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
    public void setGender( Long gender ) {
        this.gender = gender ;
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
    public void setAddress( String address ) {
        this.address = address ;
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
    public void setIdentityNumber( String identityNumber ) {
        this.identityNumber = identityNumber ;
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
    public void setDateOfBirth( Date dateOfBirth ) {
        this.dateOfBirth = dateOfBirth ;
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
    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber ;
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
    public void setEmail( String email ) {
        this.email = email ;
    }

    /**
     * Get the "email" field value
     * @return the field value
     */
    public String getEmail() {
        return this.email;
    }

}
