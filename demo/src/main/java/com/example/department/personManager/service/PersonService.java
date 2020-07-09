/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.personManager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.ChartBean;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.department.departmentManager.bo.ApartmentDetailBO;
import com.example.department.departmentManager.dao.ApartmentDetailDAO;
import com.example.department.personManager.bean.PersonBean;
import com.example.department.personManager.bo.PersonBO;
import com.example.department.personManager.dao.PersonDAO;
import com.example.department.personManager.form.PersonForm;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.dao.DepartmentDAO;
import com.example.report.bean.DashBoardBean;

/**
 * @author squadX
 * @since 14/12/2019
 * @version 1.0
 */
@Service
public class PersonService {

    @Autowired
    private PersonDAO persondao;
    @Autowired
    private ApartmentDetailDAO departmentDAO;
    @Autowired
    private UttData vfData;

    /**
     * findById
     * @param PersonId
     * @return
     */
    public PersonBO findById(Long PersonId) {
        return persondao.findById(PersonId).orElse(null);
    }

    /**
     * getDatatables
     * @param personForm
     * @return
     * @throws Exception 
     */
    public DataTableResults<PersonBean> getDatatables(PersonForm personForm) throws Exception {
        return persondao.getDatatables(vfData, personForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(PersonBO entity) {
        persondao.save(entity);
    }
    
    public boolean personHaveApart(Long idPerson) {
    	List<ApartmentDetailBO> lst = departmentDAO.findByIdPerson(idPerson);
    	if(lst == null || lst.size() == 0) {
    		return false;
    	}
    	return true;
    }

    /**
     * delete
     * @param entity
     */
    public void delete(PersonBO entity) {
        persondao.delete(entity);
    }
    
    public List<PersonBO> findByCodeOrName(String str) {
    	return persondao.findByCodeOrName('%'+str+'%');
    }
    
    public List<PersonBO> findByPersonInDepartId(Long idApartmen) throws Exception {
    	return persondao.getPersonInDepartId(vfData, idApartmen).getData();
    }
    
    public List<PersonBO> findByPersonbyDepartId(Long idApartmen) throws Exception {
    	return persondao.findByPersonInDepartId(idApartmen);
    }

    public  List<ChartBean> amountPersonByYear(Long year) {
        return persondao.amountPersonByYear(vfData, year);
    }
    
    public Long countPerson() {
        List<DashBoardBean> lst = persondao.countPerson(vfData);
        if(CommonUtil.isNullOrEmpty(lst)) {
            return 0L;
        } else {
            return lst.get(0).getTotalPerson();
        }
    }
    
    public List<PersonBean> amountPersonByYearAndMonth( Long year, Long month) {
        return persondao.amountPersonByYearAndMonth(vfData, year, month);
    }
}
