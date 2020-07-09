package com.example.bill.billServiceType.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Currency;

@Entity
@Table(name = "bill_service_detail")
public class BillOrtherDetailBo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;
    
    @Column(name="price")
    private Long price;
    
    @Column(name="id_service_type")
    private Long idType;
    
    @Column(name ="id_bill_service")
    private Long idBillOrther;
    
    @Column(name="unit")
    private String unit;
    
    
    public String getUnit() {
        return unit;
    }


    
    public void setUnit(String unit) {
        this.unit = unit;
    }


    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getAmount() {
        return amount;
    }

    
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    
    public Long getPrice() {
        return price;
    }

    
    public void setPrice(Long price) {
        this.price = price;
    }

    
    public Long getIdType() {
        return idType;
    }

    
    public void setIdType(Long idType) {
        this.idType = idType;
    }

    
    public Long getIdBillOrther() {
        return idBillOrther;
    }

    
    public void setIdBillOrther(Long idBillOrther) {
        this.idBillOrther = idBillOrther;
    }
    
    
}
