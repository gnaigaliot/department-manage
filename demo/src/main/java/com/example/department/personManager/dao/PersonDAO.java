/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.department.personManager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.common.ChartBean;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.department.personManager.bean.PersonBean;
import com.example.department.personManager.bo.PersonBO;
import com.example.department.personManager.form.PersonForm;
import com.example.report.bean.DashBoardBean;


/**
 * @author squadX
 * @version 1.0
 * @since 14/12/2019
 */
@Transactional
@Repository
public interface PersonDAO extends JpaRepository<PersonBO, Long> {
    /**
     * List all Person
     */
    public List<PersonBO> findAll();

    @Query("SELECT p FROM PersonBO p WHERE p.code like :str or p.name like :str")
    public List<PersonBO> findByCodeOrName(@Param("str") String str);

    @Query("SELECT p FROM PersonBO p join ApartmentDetailBO a " +
            "on p.id = a.idPerson  and a.idApartment = :idDepart")
    public List<PersonBO> findByPersonInDepartId(@Param("idDepart") Long idDepart);


    /**
     * get data by datatable
     *
     * @param vfData
     * @param formData
     * @return
     * @throws Exception 
     */
    public default DataTableResults<PersonBean> getDatatables(UttData vfData, PersonForm formData) throws Exception {
        List<Object> paramList = new ArrayList<>();
        String sql = " SELECT ";
        sql += "        id As id           ";
        sql += "       ,code As code         ";
        sql += "       ,name As name         ";
        sql += "       ,gender As gender       ";
        sql += "       ,address As address      ";
        sql += "       ,identity_number As identityNumber ";
        sql += "       ,date_of_birth As dateOfBirth  ";
        sql += "       ,phone_number As phoneNumber  ";
        sql += "       ,email As email        ";
        sql += "       FROM person p ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getId(), strCondition, paramList, "id");
        CommonUtil.filter(formData.getCode(), strCondition, paramList, "code");
        CommonUtil.filter(formData.getName(), strCondition, paramList, "name");
        CommonUtil.filter(formData.getGender(), strCondition, paramList, "gender");
        CommonUtil.filter(formData.getAddress(), strCondition, paramList, "address");
        CommonUtil.filter(formData.getIdentityNumber(), strCondition, paramList, "identity_number");
        CommonUtil.filter(formData.getDateOfBirth(), strCondition, paramList, "date_of_birth");
        CommonUtil.filter(formData.getPhoneNumber(), strCondition, paramList, "phone_number");
        CommonUtil.filter(formData.getEmail(), strCondition, paramList, "email");

        String lstApart = formData.getLstApartmentId();
        if(lstApart != null && !CommonUtil.isNullOrEmpty(lstApart.replace('[', ' ').replace(']', ' ')) ) {
        	lstApart = lstApart.replace('[', '(').replace(']', ')');
        	String cond = " and EXISTS (select * from apartment_detail ad "
        			+ "		where ad.id_person = p.id and ad.id_apartment in " + lstApart+ " )";
        	strCondition.append(cond);
        }
        
        String selectFields = " order by id ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, PersonBean.class);
    }
    
    
    public default DataTableResults<PersonBO> getPersonInDepartId(UttData vfData, Long idDepart) throws Exception {
        List<Object> paramList = new ArrayList<>();
        String sql = " SELECT ";
        sql += "        p.id As id           ";
        sql += "       ,code As code         ";
        sql += "       ,name As name         ";
        sql += "       ,gender As gender       ";
        sql += "       ,address As address      ";
        sql += "       ,identity_number As identityNumber ";
        sql += "       ,date_of_birth As dateOfBirth  ";
        sql += "       ,phone_number As phoneNumber  ";
        sql += "       ,email As email        ";
        sql += "       ,ad.is_Main As isMain        ";
        sql += "       FROM person p ";
        sql += "       JOIN apartment_detail ad on ad.id_person = p.id ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(idDepart, strCondition, paramList, "ad.id_apartment");
        
        String selectFields = " order by p.id ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, PersonBO.class, Integer.MAX_VALUE);
    }
    
    public default List<ChartBean> amountPersonByYear(UttData uttData, Long year) {
        String hql = "  with temp as ( SELECT MONTH(start_date) AS label, COUNT(id_person) AS data " + 
                " FROM apartment_detail " + 
                " WHERE YEAR(start_date) <= :year " + 
                "      AND (end_date IS NULL OR YEAR(end_date) > :year) " + 
                " GROUP BY MONTH(start_date) " + 
                " ORDER BY MONTH(start_date) ) " + 
                " select m.month as label, ifnull(temp.data,0) as data " + 
                " from v_month m   " + 
                " left join temp on m.month = temp.label " + 
                " ORDER BY  m.month asc " ;
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("year", year);
        uttData.setResultTransformer(query, ChartBean.class);
        return query.list();
    }
    
    public default List<PersonBean> amountPersonByYearAndMonth(UttData uttData, Long year, Long month) {
        String hql = "  SELECT DISTINCT id_person AS id " + 
                " FROM apartment_detail ad " + 
                " WHERE ( YEAR(ad.start_date) < :year and (ad.end_date is null or YEAR(ad.end_date) > :year or (YEAR(ad.end_date) = :year and MONTH(ad.end_date) >=:month ) )) " + 
                "  or ( YEAR(ad.start_date) = :year AND MONTH(ad.start_date) <= :month and (ad.end_date is null or YEAR(ad.end_date) > :year or (YEAR(ad.end_date) = :year and MONTH(ad.end_date) >=:month ) )  )" ;
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("year", year);
        query.setParameter("month", month);
        uttData.setResultTransformer(query, PersonBean.class);
        return query.list();
    }
    
    public default List<DashBoardBean> countPerson(UttData uttData) {
        String hql = "  select count(distinct ad.id_person) as totalPerson " + 
                " from apartment_detail ad " + 
                " where ad.end_date is null or ad.end_date >= CURRENT_DATE() " ;
        SQLQuery query = uttData.createSQLQuery(hql);
        uttData.setResultTransformer(query, DashBoardBean.class);
        return  query.list();
    }
}
