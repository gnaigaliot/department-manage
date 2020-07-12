package com.example.employee.service;

import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.dao.DepartmentDAO;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private UttData vfData;

    public DataTableResults<DepartmentBean> search(DepartmentForm departmentForm) {
        return departmentDAO.search(vfData, departmentForm);
    }

    public List<DepartmentBO> getAll() {
        return departmentDAO.findAllDepartment();
    }
    public DepartmentBO getById(Long departmentId) {
        return departmentDAO.findDepartmentById(departmentId);
    }


    public DataTableResults<EmployeeBean> getEmployeesOfDepartmentByDepartmentId(Long departmentId, EmployeeForm employeeForm) {
        return departmentDAO.getEmployeesOfDepartmentByDepartmentId(vfData, departmentId, employeeForm);
    }
    @Transactional
    public void saveOrUpdate(DepartmentBO entity) {
        departmentDAO.save(entity);
    }


    public void deleteById(Long departmentId) {
        departmentDAO.deleteById(departmentId);
    }

    public List<EmployeeBean> getEmployeeBeanListById(Long idDepartment) {
        return departmentDAO.getEmployeeBeanListById(vfData, idDepartment);
    }
}
