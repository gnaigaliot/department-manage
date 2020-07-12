package com.example.employee.service;

import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.PositionBean;
import com.example.employee.bo.PositionBO;
import com.example.employee.dao.PositionDAO;
import com.example.employee.form.PositionForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionDAO positionDAO;

    @Autowired
    private UttData vfData;

    public List<PositionBO> getAll() {
        return positionDAO.findAllPosition();
    }
    public DataTableResults<PositionBean> search(PositionForm positionForm) {
        return positionDAO.search(vfData, positionForm);
    }

    public PositionBO getById(Long id) {
        return positionDAO.findPositionById(id);
    }

    @Transactional
    public void saveOrUpdate(PositionBO entity) {
        positionDAO.save(entity);
    }

    public void deleteById(Long id) {
        positionDAO.deleteById(id);
    }

}
