/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.department.departmentManager.form;

import java.util.Date;

/**
 * ApartmentDetailForm class
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class ApartmentDetailForm {

    private Long    id;
    private Date       startDate;
    private Date       endDate;
    private Long    idPerson;
    private Long    idApartment;


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
     * Set the "startDate" field value
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
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
    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    /**
     * Get the "idApartment" field value
     * @return the field value
     */
    public Long getIdApartment() {
        return this.idApartment;
    }

}
