/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.departmentManager.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.department.departmentManager.bean.ApartmentBean;
import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.bo.ApartmentDetailBO;
import com.example.department.departmentManager.dao.ApartmentDAO;
import com.example.department.departmentManager.form.ApartmentForm;
import com.example.department.personManager.bo.PersonBO;
import com.example.department.personManager.service.PersonService;

/**
 * @author squadX
 * @since 16/12/2019
 * @version 1.0
 */
@Service
public class ApartmentService {

    @Autowired
    private ApartmentDAO apartmentdao;
    @Autowired
    private UttData vfData;
    @Autowired
    private PersonService personService;

    /**
     * findById
     * @param ApartmentId
     * @return
     */
    public ApartmentBO findById(Long ApartmentId) {
        return apartmentdao.findById(ApartmentId).orElse(null);
    }
    
    public boolean duplicatePersonInApart(List<ApartmentDetailBO> lst) {
        if(lst == null || lst.size() == 0) {
        	return false;
        }
        HashMap<Long, Long> hashPerson = new HashMap<>();
        for (ApartmentDetailBO apartmentDetailBO : lst) {
			if (hashPerson.containsKey(apartmentDetailBO.getIdPerson())) {
				return true;
			} else
			{
				hashPerson.put(apartmentDetailBO.getIdPerson(), null);
			}
		}
        return false;
    }
    
    public boolean duplicateChuNha(List<ApartmentDetailBO> lst) {
        if(lst == null || lst.size() == 0) {
        	return false;
        }
        int dem = 0;
        for (ApartmentDetailBO apartmentDetailBO : lst) {
			if (apartmentDetailBO.getIsMain() != null && apartmentDetailBO.getIsMain() == 1) {
				dem ++;
			} 
		}
        if(dem > 1) {
        	return true;
        }
        return false;
    }
    
    public boolean apartHavePerson(Long idApart) throws Exception {
    	List<PersonBO> lstBO = personService.findByPersonbyDepartId(idApart);
    	if(CommonUtil.isNullOrEmpty(lstBO)) {
    		 return false;
    	}
       return true;
    }

    /**
     * getDatatables
     * @param apartmentForm
     * @return
     */
    public DataTableResults<ApartmentBean> getDatatables(ApartmentForm apartmentForm) {
        return apartmentdao.getDatatables(vfData, apartmentForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(ApartmentBO entity) {
        apartmentdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(ApartmentBO entity) {
        apartmentdao.delete(entity);
    }
    
    public List<ApartmentBO> findByStatus(Long status){
        return apartmentdao.findByStatus(status);
    }
    /**
     * 
     */
    public List<ApartmentBO> getAllApartment() {
        return apartmentdao.getAllApartment();
    }
    
    public boolean checkDuplicateCode(String code, Long id) {
    	List<ApartmentBO> lstBO = apartmentdao.findByCodeAndId(code, id);
    	if(lstBO!= null && lstBO.size() > 0 ) {
    		return true;
    	}
    	return false;
    }
    
    public  List<ApartmentBean> getListActiveId() {
        return apartmentdao.getListActiveId(vfData);
    }
}
