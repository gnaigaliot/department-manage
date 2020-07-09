/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.departmentManager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.department.departmentManager.bean.ApartmentDetailBean;
import com.example.department.departmentManager.bo.ApartmentDetailBO;
import com.example.department.departmentManager.dao.ApartmentDetailDAO;
import com.example.department.departmentManager.form.ApartmentDetailForm;

/**
 * @author squadX
 * @since 16/12/2019
 * @version 1.0
 */
@Service
public class ApartmentDetailService {

    @Autowired
    private ApartmentDetailDAO apartmentDetaildao;
    @Autowired
    private UttData vfData;

    /**
     * findById
     * @param ApartmentDetailId
     * @return
     */
    public ApartmentDetailBO findById(Long ApartmentDetailId) {
        return apartmentDetaildao.findById(ApartmentDetailId).orElse(null);
    }
    
    
    public List<ApartmentDetailBO> findByIdApartment(Long ApartmentId) {
    	return apartmentDetaildao.findByIdApartment(ApartmentId);
    }

    /**
     * getDatatables
     * @param apartmentDetailForm
     * @return
     */
    public DataTableResults<ApartmentDetailBean> getDatatables(ApartmentDetailForm apartmentDetailForm) {
        return apartmentDetaildao.getDatatables(vfData, apartmentDetailForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(ApartmentDetailBO entity) {
        apartmentDetaildao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(ApartmentDetailBO entity) {
        apartmentDetaildao.delete(entity);
    }
    
    public void deleteByIdApartment(Long idApartment) {
    	List<ApartmentDetailBO> lstBO = apartmentDetaildao.findByIdApartment(idApartment);
    	if(!CommonUtil.isNullOrEmpty(lstBO)) {
    		apartmentDetaildao.deleteAll(lstBO);
    	}
    }
}
