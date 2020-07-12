package com.example.bill.billServiceType.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_service")
public class BillOrtherBo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

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

    @Column(name = "id_Apartment")
    private Long idApartment;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "code_apartment")
    private String codeApartment;

    public String getCodeApartment() {
        return codeApartment;
    }

    public void setCodeApartment(String codeApartment) {
        this.codeApartment = codeApartment;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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

    public Long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

}
