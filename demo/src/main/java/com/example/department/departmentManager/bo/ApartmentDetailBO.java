/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.departmentManager.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author squadX
 * @since 16/12/2019
 * @version 1.0
 */
@Entity
@Table(name = "apartment_detail")
public class ApartmentDetailBO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "id_person")
    private Long idPerson;

    @Column(name = "id_apartment")
    private Long idApartment;
    
    @Column(name = "is_main")
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
    /**
     * Set the "startDate" field value
     * @param startDate
     */
    public void setStartDate( Date startDate ) {
        this.startDate = startDate ;
    }

    /**
     * Get the "startDate" field value
     * @return the field value
     */
    public Date getStartDate() {
        return this.startDate;
    }
    /**
     * Set the "endDate" field value
     * @param endDate
     */
    public void setEndDate( Date endDate ) {
        this.endDate = endDate ;
    }

    /**
     * Get the "endDate" field value
     * @return the field value
     */
    public Date getEndDate() {
        return this.endDate;
    }
    /**
     * Set the "idPerson" field value
     * @param idPerson
     */
    public void setIdPerson( Long idPerson ) {
        this.idPerson = idPerson ;
    }

    /**
     * Get the "idPerson" field value
     * @return the field value
     */
    public Long getIdPerson() {
        return this.idPerson;
    }
    /**
     * Set the "idApartment" field value
     * @param idApartment
     */
    public void setIdApartment( Long idApartment ) {
        this.idApartment = idApartment ;
    }

    /**
     * Get the "idApartment" field value
     * @return the field value
     */
    public Long getIdApartment() {
        return this.idApartment;
    }

}
