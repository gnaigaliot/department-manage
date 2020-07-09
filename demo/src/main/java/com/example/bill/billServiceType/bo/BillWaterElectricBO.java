package com.example.bill.billServiceType.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_water_electric")
public class BillWaterElectricBO {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;
	
	@Column(name = "code_apartment")
	private String codeApartment;

	@Column(name = "old_number")
	private Double oldNumber;
	
	@Column(name = "new_number")
	private Double newNumber;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "id_apartment")
	private Long idApartment;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "id_bill_type")
	private Long idBillType;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "edited_date")
	private Date editeDate;
	
	@Column(name = "edited_by")
	private String editedBy;
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	public String getCodeApartment() {
		return codeApartment;
	}

	public void setCodeApartment(String codeApartment) {
		this.codeApartment = codeApartment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEditeDate() {
		return editeDate;
	}

	public void setEditeDate(Date editeDate) {
		this.editeDate = editeDate;
	}

	

	
    public String getEditedBy() {
        return editedBy;
    }

    
    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    
    public String getCreatedBy() {
        return createdBy;
    }

    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

	public Long getIdBillType() {
		return idBillType;
	}

	public void setIdBillType(Long idBillType) {
		this.idBillType = idBillType;
	}
	
	
	
}
