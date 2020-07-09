package com.example.employee.service;

import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bean.StatisticalPersonBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.EmployeeBO;
import com.example.employee.dao.EmployeeDAO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.EmployeeForm;
import com.example.employee.form.SearchStatisticalForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.dao.DepartmentDAO;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private UttData vfData;
    public List<EmployeeBO> getAll() {
        return employeeDAO.findAllEmployee();
    }

    /**
     *
     * @param employeeForm: employee form
     * @return
     */
    public DataTableResults<EmployeeBean> search(EmployeeForm employeeForm) {
        return employeeDAO.search(vfData, employeeForm);
    }
    public List<EmployeeBean> getEmployeeBeanById(Long employeeId) {
        return employeeDAO.findEmployeeBeanById(vfData, employeeId);
    }
    public EmployeeBO getEmployeeBOById(Long employeeId) {
        return employeeDAO.findEmployeeBOById(employeeId);
    }

    public void deleteById(Long employeeId) { employeeDAO.deleteById(employeeId);}

    public void saveOrUpdate(EmployeeBO employeeBO) {
        employeeDAO.save(employeeBO);
    }
    public DataTableResults<StatisticalPersonBean> searchStatistical(SearchStatisticalForm employeeForm) {
        return employeeDAO.searchStatistical(vfData, employeeForm);
    }

}
