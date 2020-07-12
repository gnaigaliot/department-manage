/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.departmentManager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.department.departmentManager.bean.ApartmentBean;
import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.form.ApartmentForm;

/**
 * @author squadX
 * @since 16/12/2019
 * @version 1.0
 */
@Transactional
@Repository
public interface ApartmentDAO extends JpaRepository<ApartmentBO, Long>
{
	
	@Query("Select a from ApartmentBO a")
	public List<ApartmentBO> getAllApartment();
	
	@Query("Select a from ApartmentBO a where a.code = :code and a.id != :id")
	public List<ApartmentBO> findByCodeAndId (@Param("code") String code, @Param("id")  Long id);
    /**
     * List all Apartment
     */
    public List<ApartmentBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param ApartmentForm
     * @return
     */
    public default DataTableResults<ApartmentBean> getDatatables(UttData vfData, ApartmentForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        a.id As id           ";
        sql += "       ,code As code         ";
        sql += "       ,name As name         ";
        sql += "       ,price As price        ";
        sql += "       ,area As area         ";
        sql += "       ,description As description  ";
        sql += "       ,status As status       ";
        sql += "       ,created_date As createdDate  ";
        sql += "       ,edited_date As editedDate   ";
        sql += "       ,created_by As createdBy    ";
        sql += "       ,edited_by As editedBy     ";
        sql += "       ,(select count(*) from apartment_detail ad "
        		+ "		where ad.id_apartment = a.id  ) As amountPersonOfApart     ";
        sql += "       FROM apartment a ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");

        CommonUtil.filter(formData.getId(), strCondition, paramList, "id");
        CommonUtil.filter(formData.getCode(), strCondition, paramList, "code");
        CommonUtil.filter(formData.getName(), strCondition, paramList, "name");
        
        Long haveLive = formData.getHaveLive();
        Long notHaveLive = formData.getNotHaveLive();
        if((haveLive == 1L && notHaveLive == 0L) || (haveLive == 0L && notHaveLive == 1)) {
        	String cond =" and ";
        	if(haveLive == 1L) {
        		cond += " exists ";
        	} else {
        		cond += " not exists ";
        	}
        	cond += " ( select * from apartment_detail addd where addd.id_apartment = a.id) ";
        	strCondition.append(cond);
        }
        
        String selectFields = " order by id";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, ApartmentBean.class);
    }
    
    public List<ApartmentBO> findByStatus(Long status);

    public ApartmentBO findByCode(String codeApartment);
    
    public default List<ApartmentBean> getListActiveId(UttData uttData) {
        String hql = " select distinct a.id as id " + 
                "from apartment a " + 
                "join apartment_detail ad on a.id = ad.id_apartment " + 
                "where ad.end_date is null or ad.end_date >= CURRENT_DATE() " ;
        SQLQuery query = uttData.createSQLQuery(hql);
        uttData.setResultTransformer(query, ApartmentBean.class);
        return  query.list();
    }
}
