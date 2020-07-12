package com.example.report.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bill.billServiceType.service.BillWaterElectricService;
import com.example.common.ChartBean;
import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.service.ApartmentService;
import com.example.department.personManager.service.PersonService;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.form.EmployeeForm;
import com.example.employee.service.WorkProcessService;
import com.example.report.bean.DashBoardBean;
import com.example.report.bean.StatisticalBean;
import com.example.report.form.statisticalForm;

@RestController
@RequestMapping("/statistical")
public class StatisticalController {

    @Autowired
    private PersonService personService;
    
    @Autowired
    private BillWaterElectricService billWaterElectricService;
    
    @Autowired
    private WorkProcessService workProcessService;
    
    @Autowired
    private ApartmentService apartmentService;
    
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<StatisticalBean> search(HttpServletRequest req, statisticalForm form){
        return billWaterElectricService.search(form);
    }
    
}
