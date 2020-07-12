package com.example.bill.billServiceType.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BillWaterElectricForm {

    private Long id;
    private String codeBill;
    private Double oldNumber;
    private Double newNumber;
    private Double amount;
    private Long fromDate;
    private Long toDate;
    private Long status;
    private Date createDate;
    private Date editedDate;
    private Long idApartment;
    private Double totalPrice;
    private Long type;
    private String codeApartment;

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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCodeApartment() {
        return codeApartment;
    }

    public void setCodeApartment(String codeApartment) {
        this.codeApartment = codeApartment;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

}
