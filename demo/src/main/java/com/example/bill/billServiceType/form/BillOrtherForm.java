package com.example.bill.billServiceType.form;

import java.util.Date;
import java.util.List;

public class BillOrtherForm {

    private Long id;
    private String code;
    private Long fromDate;
    private Long toDate;
    private String status;
    private Date createdDate;
    private Date editedDate;
    private String createdBy;
    private String editedDy;
    private Long idApartment;
    private Long price;
    private String codeApartment;
    private String name;
    private String numberPhone;
    private Long totalPrice;

    public String getCodeApartment() {
        return codeApartment;
    }

    public void setCodeApartment(String codeApartment) {
        this.codeApartment = codeApartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        if (fromDate == null) {
            return null;
        }
        return new Date(fromDate);
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        if (toDate == null) {
            return null;
        }
        return new Date(toDate);
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getEditedDy() {
        return editedDy;
    }

    public void setEditedDy(String editedDy) {
        this.editedDy = editedDy;
    }

    public Long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
