/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.departmentManager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.department.departmentManager.bean.ApartmentDetailBean;
import com.example.department.departmentManager.bo.ApartmentDetailBO;
import com.example.department.departmentManager.form.ApartmentDetailForm;
import com.example.department.personManager.bo.PersonBO;

/**
 * @author squadX
 * @since 16/12/2019
 * @version 1.0
 */
@Transactional
@Repository
public interface ApartmentDetailDAO extends CrudRepository<ApartmentDetailBO, Long>
{
    /**
     * List all ApartmentDetail
     */
    public List<ApartmentDetailBO> findAll();
    public List<ApartmentDetailBO> findByIdApartment(Long idApartment);
    public List<ApartmentDetailBO> findByIdPerson(Long idPerson);

   
    /**
     * get data by datatable
     * @param vfData
     * @param ApartmentDetailForm
     * @return
     */
    public default DataTableResults<ApartmentDetailBean> getDatatables(UttData vfData, ApartmentDetailForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        id As id           ";
        sql += "       ,start_date As startDate    ";
        sql += "       ,end_date As endDate      ";
        sql += "       ,id_person As idPerson     ";
        sql += "       ,id_apartment As idApartment  ";
        sql += "       FROM apartment_detail ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getId(), strCondition, paramList, "id");
        CommonUtil.filter(formData.getStartDate(), strCondition, paramList, "start_date");
        CommonUtil.filter(formData.getEndDate(), strCondition, paramList, "end_date");
        CommonUtil.filter(formData.getIdPerson(), strCondition, paramList, "id_person");
        CommonUtil.filter(formData.getIdApartment(), strCondition, paramList, "id_apartment");

        String selectFields = " id , startDate, endDate, idPerson, idApartment ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, ApartmentDetailBean.class);
    }
}
