package com.example.employee.dao;

import com.example.common.ChartBean;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.PositionBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.PositionBO;
import com.example.employee.bo.WorkProcessBO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.PositionForm;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface WorkProcessDAO extends CrudRepository<WorkProcessBO, Long>{

    @Query(value = "select * from work_process wp where wp.id_employee = :idEmployee",
            nativeQuery = true)
    WorkProcessBO getWorkProcessByEmployeeId(@Param("idEmployee") Long idEmployee);
    
    
    public default List<ChartBean> amountEmployeeByYear(UttData uttData, Long year) {
        String hql = "  with temp as ( select id_department as id, COUNT(id_employee) AS data " + 
                "FROM work_process " + 
                "WHERE YEAR(start_date) <= :year " + 
                "      AND (end_date IS NULL OR YEAR(end_date) > :year) " + 
                "GROUP BY id_department ) " + 
                "select d.name label, ifnull(temp.data,0) as data " + 
                "from department d  " + 
                "left join temp on d.id = temp.id " + 
                "ORDER BY  d.name asc " ;
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("year", year);
        uttData.setResultTransformer(query, ChartBean.class);
        return query.list();
    }
}

