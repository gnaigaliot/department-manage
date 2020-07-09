package com.example.employee.dao;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.form.DepartmentForm;

import com.example.employee.form.EmployeeForm;
import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DepartmentDAO extends CrudRepository<DepartmentBO, Long> {

    @Query(value = "select * from department d", nativeQuery = true)
    public List<DepartmentBO> findAllDepartment();

    @Query(value = "select * from department d where id = :id", nativeQuery = true)
    DepartmentBO findDepartmentById(@Param("id") Long id);

    public default List<EmployeeBean> getEmployeeBeanListById(UttData uttData, Long idDepartment) {
        String selectQuery = "select" +
                " e.id as id " +
                " ,d.id as idDepartment " +
                " ,e.name as name " +
                " ,e.code as code " +
                " ,p.name as positionsName " +
                " from department d left join work_process wp on d.id = wp.id_department " +
                " left join employee e on e.id = wp.id_employee " +
                " left join positions p on p.id = wp.id_positions " +
                " where d.id = " + Long.toString(idDepartment);
        SQLQuery query = uttData.createSQLQuery(selectQuery);
        System.out.println(selectQuery);
        uttData.setResultTransformer(query, EmployeeBean.class);
        return query.list();
    }
    // For search
    public default DataTableResults<DepartmentBean> search(UttData vfData, DepartmentForm formData) {
        List<Object> paramList = new ArrayList<>();
        String sql = " SELECT ";
        sql += "        id As id           ";
        sql += "       ,code As code         ";
        sql += "       ,name As name         ";
        sql += "       ,created_by As createdBy      ";
        sql += "       ,edited_by As editedBy ";
        sql += "       ,created_date As createdDate ";
        sql += "       ,edited_date As editedDate ";
        sql += "       ,status As status ";
        sql += "       FROM department ";

        StringBuilder strCondition = new StringBuilder(" WHERE status = 1 ");

        CommonUtil.filter(formData.getId(), strCondition, paramList, "id");
        CommonUtil.filter(formData.getCode(), strCondition, paramList, "code");
        CommonUtil.filter(formData.getName(), strCondition, paramList, "name");

        String selectFields = " order by id ";

        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, DepartmentBean.class);
    }

    // List all employee
    public default DataTableResults<EmployeeBean> getEmployeesOfDepartmentByDepartmentId(UttData uttData, Long departmentId, EmployeeForm employeeForm) {
        List<Object> paramList = new ArrayList<>();
        String sql = "select" +
                " e.id as id " +
                " ,e.name as name " +
                " ,e.email as email " +
                " ,e.date_of_bird as dateOfBird " +
                " ,e.code as code " +
                " ,d.id as idDepartment " +
                " ,p.name as positionsName " +
                " from department d left join work_process wp on d.id = wp.id_department " +
                " left join employee e on e.id = wp.id_employee " +
                " left join positions p on p.id = wp.id_positions ";
//                " where d.id = " + Long.toString(departmentId);

        StringBuilder strCondition = new StringBuilder("  where d.status = 1  ");

        CommonUtil.filter(departmentId, strCondition, paramList, "d.id");
        CommonUtil.filter(employeeForm.getCode(), strCondition, paramList, "e.code");
        CommonUtil.filter(employeeForm.getName(), strCondition, paramList, "e.name");
        CommonUtil.filter(employeeForm.getPositionsName(), strCondition, paramList, "p.name");
        CommonUtil.filter(employeeForm.getEmail(), strCondition, paramList, "e.email");

        String selectFields = " order by e.id ";

        return uttData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, EmployeeBean.class);
    }

    // For delete
    @Modifying
    @Query(value = "update department d set status=0 where id = :departmentId", nativeQuery = true)
    void deleteById(@Param("departmentId") Long departmentId);


}

