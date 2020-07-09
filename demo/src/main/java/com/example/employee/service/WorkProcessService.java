package com.example.employee.service;

import com.example.common.ChartBean;
import com.example.common.UttData;
import com.example.employee.bo.PositionBO;
import com.example.employee.bo.WorkProcessBO;
import com.example.employee.dao.WorkProcessDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class WorkProcessService {
    @Autowired
    private WorkProcessDAO workProcessDAO;

    @Autowired
    private UttData vfData;
    
    @Transactional
    public void saveOrUpdate(WorkProcessBO entity) {
        workProcessDAO.save(entity);
    }

    @Transactional
    public WorkProcessBO getWorkProcessByEmployeeId(Long idEmployee) { return workProcessDAO.getWorkProcessByEmployeeId(idEmployee);}

    public List<ChartBean> amountEmployeeByYear( Long year) {
        return workProcessDAO.amountEmployeeByYear(vfData, year);
    }
}
