package com.example.bill.billServiceType.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bill.billServiceType.bean.BillServiceTypeBean;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.dao.BillServiceTypeDao;
import com.example.bill.billServiceType.form.BillServiceTypeForm;
import com.example.common.DataTableResults;
import com.example.common.UttData;
@Service
public class BillServiceTypeService {
	@Autowired
	private BillServiceTypeDao billServiceTypeDao;
	@Autowired
    private UttData vfData;
	
	public DataTableResults<BillServiceTypeBean> getDatatables(BillServiceTypeForm form) {
		
		return billServiceTypeDao.getDatatables(vfData,form);
	}

	public BillServiceTypeBO findById(Long id) {
		return billServiceTypeDao.findById(id).orElse(null);
	}
	
	 @Transactional
	    public void saveOrUpdate(BillServiceTypeBO entity) {
		 billServiceTypeDao.save(entity);
	    }

	public void delete(BillServiceTypeBO bo) {
		billServiceTypeDao.deleteById(bo.getId());
		
	}

	public BillServiceTypeBO findByCode(String code) {
		return billServiceTypeDao.findByCode(code).orElse(null);
	}
}
