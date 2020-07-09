package com.example.bill.billServiceType.form;

public class BillOrtherDetailForm {

    private Long id;
    private Long type;
    private Long price;
    private Long amountUnit;
    private String unit;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(Long amountUnit) {
        this.amountUnit = amountUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
