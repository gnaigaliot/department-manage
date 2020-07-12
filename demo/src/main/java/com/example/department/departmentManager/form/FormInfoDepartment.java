package com.example.department.departmentManager.form;

import java.util.List;

import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.bo.ApartmentDetailBO;
import com.example.department.personManager.bo.PersonBO;

public class FormInfoDepartment {
	private ApartmentBO apartmentForm; 
	private List<ApartmentDetailBO> lstApartmentForm;
	private List<PersonBO> lstPersonIn;
	
	public List<PersonBO> getLstPersonIn() {
		return lstPersonIn;
	}
	public void setLstPersonIn(List<PersonBO> lstPersonIn) {
		this.lstPersonIn = lstPersonIn;
	}
	public ApartmentBO getApartmentForm() {
		return apartmentForm;
	}
	public void setApartmentForm(ApartmentBO apartmentForm) {
		this.apartmentForm = apartmentForm;
	}
	public List<ApartmentDetailBO> getLstApartmentForm() {
		return lstApartmentForm;
	}
	public void setLstApartmentForm(List<ApartmentDetailBO> lstApartmentForm) {
		this.lstApartmentForm = lstApartmentForm;
	}
	
	
	
	
	
	
	
}
