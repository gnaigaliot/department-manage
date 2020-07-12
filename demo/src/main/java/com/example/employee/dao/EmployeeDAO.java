package com.example.employee.dao;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bean.StatisticalPersonBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.EmployeeBO;
import com.example.employee.form.DepartmentForm;

import com.example.employee.form.EmployeeForm;
import com.example.employee.form.SearchStatisticalForm;
import com.example.report.bean.StatisticalBean;
import com.example.report.form.statisticalForm;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
public interface EmployeeDAO extends CrudRepository<EmployeeBO, Long>{

    @Query(value = "select * from employee e where e.status=1 and e.is_working=1", nativeQuery = true)
    public List<EmployeeBO> findAllEmployee();

    @Query ( value = "select * from employee e where e.id = :idEmployee", nativeQuery = true)
    EmployeeBO findEmployeeBOById(@Param("idEmployee") Long idEmployee);

    public default List<EmployeeBean> findEmployeeBeanById(UttData uttData, Long idEmployee) {
        String selectQuery = "select " +
                " e.id as id" +
                " ,e.code as code" +
                " ,e.name as name" +
                " ,e.date_of_bird as dateOfBird" +
                " ,e.gender as gender" +
                " ,e.email as email" +
                " ,e.phonenumber as phonenumber" +
                " ,e.created_by as createdBy" +
                " ,e.created_date as createdDate" +
                " ,e.is_working as isWorking" +
                " ,d.id as idDepartment" +
                " ,d.name as departmentName" +
                " ,p.id as idPositions" +
                " ,p.name as positionsName" +
                " ,p.salary as salary " +
                " ,wp.start_date as startDate " +
                " ,wp.end_date as endDate " +
                " from employee e left join work_process wp on e.id = wp.id_employee " +
                " left join department d on d.id = wp.id_department " +
                " left join positions p on wp.id_positions = p.id " +
                " where e.id = " + Long.toString(idEmployee);
        SQLQuery query = uttData.createSQLQuery(selectQuery);
        uttData.setResultTransformer(query, EmployeeBean.class);
        return query.list();
    }
    // For search
    public default DataTableResults<EmployeeBean> search(UttData vfData, EmployeeForm formData) {
        List<Object> paramList = new ArrayList<>();
        String sql = "select " +
                "e.id as id" +
                ",e.code as code" +
                ",e.name as name" +
                ",e.date_of_bird as dateOfBird" +
                ",e.gender as gender" +
                ",e.email as email" +
                ",e.phonenumber as phonenumber" +
                ",e.created_by as createdBy" +
                ",e.created_date as createdDate" +
                ",e.is_working as isWorking" +
                ",d.id as idDepartment" +
                ",d.name as departmentName" +
                ",p.id as idPositions" +
                ",p.name as positionsName" +
                ",p.salary as salary " +
                " from employee e left join work_process wp on e.id = wp.id_employee left join department d on d.id = wp.id_department left join positions p on wp.id_positions = p.id ";

        StringBuilder strCondition = new StringBuilder(" where e.status = 1  ");

        CommonUtil.filter(formData.getId(), strCondition, paramList, "e.id");
        CommonUtil.filter(formData.getCode(), strCondition, paramList, "e.code");
        CommonUtil.filter(formData.getName(), strCondition, paramList, "e.name");
        CommonUtil.filter(formData.getGender(), strCondition, paramList, "e.gender");
        CommonUtil.filter(formData.getEmail(), strCondition, paramList, "e.email");
        CommonUtil.filter(formData.getPhonenumber(), strCondition, paramList, "e.phonenumber");
        CommonUtil.filter(formData.getCreatedBy(), strCondition, paramList, "e.created_by");
        CommonUtil.filter(formData.getIsWorking(), strCondition, paramList, "e.is_working");
        CommonUtil.filter(formData.getIdDepartment(), strCondition, paramList, "d.id");
        CommonUtil.filter(formData.getDepartmentName(), strCondition, paramList, "d.name");
        CommonUtil.filter(formData.getIdPositions(), strCondition, paramList, "p.id");
        CommonUtil.filter(formData.getPositionsName(), strCondition, paramList, "p.name");
        CommonUtil.filter(formData.getSalary(), strCondition, paramList, "p.salary");

        String selectFields = " order by e.id desc";
        System.out.println("String query: "+ sql.toString());
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, EmployeeBean.class);
    }
    
    public default DataTableResults<StatisticalPersonBean> searchStatistical(UttData vfData, SearchStatisticalForm form){
        List<Object> paramList = new ArrayList<>();
	        String sql = 	  " SELECT "
			        		+ "		count(distinct p.id) as so_nguoi"
			        		+ ",	month(apd.start_date) as month"
			        		+ ",	year(apd.start_date) as year"
			        		+ ",	count(distinct e.id) as so_nhan_vien"
			        		+ " 	FROM "
			        		+ "		departmentmanager.apartment_detail as apd inner join departmentmanager.person as p"
			        		+ "		on p.id = apd.id_person"
			        		+ ",	departmentmanager.employee as e inner join departmentmanager.work_process as w " 
			        		+ "     on e.id = w.id_employee"    
					        + "     where  month(w.start_date) = month(apd.start_date) and year(w.start_date) = year(apd.start_date)"; 
					        if(!CommonUtil.isEmpty(form.getFormDate()) && !CommonUtil.isEmpty(form.getFormDate())) {
					        	Date x = new Date(new Long(form.getFormDate()));
					        	Date y = new Date(new Long(form.getEndDate()));
					        	sql		+= "     and  apd.start_date > ? and apd.start_date < ?"
					        			+ "     and  w.start_date > ? and w.start_date < ?";
					            paramList.add(x);
					            paramList.add(y);
					            paramList.add(x);
					            paramList.add(y);
					        }
					        

        StringBuilder strCondition = new StringBuilder(" and 1 = 1  ");


        String selectFields = "group by month(apd.start_date) order by month(apd.start_date) ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, StatisticalPersonBean.class);
    }

    // For delete
    @Modifying
    @Query( value= "update employee d set status=0 where id = :employeeId", nativeQuery = true)
    void deleteById(@Param("employeeId") Long employeeId);
}
