package com.example.report.controller;

import java.util.ArrayList;
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
import com.example.common.Response;
import com.example.department.departmentManager.bean.ApartmentBean;
import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.service.ApartmentService;
import com.example.department.personManager.bean.PersonBean;
import com.example.department.personManager.service.PersonService;
import com.example.employee.service.WorkProcessService;
import com.example.report.bean.DashBoardBean;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private PersonService personService;
    
    @Autowired
    private BillWaterElectricService billWaterElectricService;
    
    @Autowired
    private WorkProcessService workProcessService;
    
    @Autowired
    private ApartmentService apartmentService;
    
    @GetMapping(value="/person/{year}")
    public @ResponseBody Response getAmoutPerson(HttpServletRequest req, @PathVariable("year") Long year) throws Exception {
        
        List<ChartBean> data = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            ChartBean bean = new ChartBean();
            bean.setLabel(String.format("Thang %s", i));
            List<PersonBean> lst = personService.amountPersonByYearAndMonth(year, new Long(i));
            if(CommonUtil.isNullOrEmpty(lst)) {
                bean.setData(0L);
            } else {
                bean.setData(new Long(lst.size()));
            }
            data.add(bean);
        }
        return new Response(null, null, data);
    }
    
    @GetMapping(value="/employee/{year}")
    public @ResponseBody Response getAmoutEmployee(HttpServletRequest req, @PathVariable("year") Long year) throws Exception {
        List<ChartBean> lst = workProcessService.amountEmployeeByYear(year);
        return new Response(null, null, lst);
    }
    
    @GetMapping(value="/bill/{year}/{type}")
    public @ResponseBody Response getTotalBill(HttpServletRequest req, @PathVariable("year") Long year
            , @PathVariable("type") Long type) throws Exception {
        List<ChartBean> lst = billWaterElectricService.totalByYear(year,type);
        return new Response(null, null, lst);
    }
    
    @GetMapping(value="/dash-board")
    public @ResponseBody Response dashDoard(HttpServletRequest req) throws Exception {
        DashBoardBean bean = new DashBoardBean();
        List<ApartmentBO> lst = apartmentService.getAllApartment();
        List<ApartmentBean> lstId = apartmentService.getListActiveId();
        Long total =0L;
        if(!CommonUtil.isNullOrEmpty(lst)) {
            total = new Long(lst.size());
        }
        if(CommonUtil.isNullOrEmpty(lstId)) {
            bean.setActive(0L);
        } else {
            bean.setActive(new Long(lstId.size()));
        }
        bean.setEmpty(Math.abs(total - bean.getActive()));
        
        total = personService.countPerson();
        bean.setTotalPerson(total);
        return new Response(null, null, bean);
    }
    
}
