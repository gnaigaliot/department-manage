package com.example.bill.billServiceType.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bill.billServiceType.bean.BillWaterElectricBean;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.bo.BillWaterElectricBO;
import com.example.bill.billServiceType.dao.BillWaterElectricDao;
import com.example.bill.billServiceType.form.BillWaterElectricForm;
import com.example.common.ChartBean;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.common.UttData;
import com.example.report.bean.StatisticalBean;
import com.example.report.form.statisticalForm;

@Service
public class BillWaterElectricService {
	
	@Autowired
	private BillWaterElectricDao billWaterElectricDao;
	@Autowired
    private UttData vfData;


	public DataTableResults<BillWaterElectricBean> getDatatables(BillWaterElectricForm form) {
		return billWaterElectricDao.getDatatables(vfData,form);
	}

	public DataTableResults<StatisticalBean> search(statisticalForm form) {
		return billWaterElectricDao.search(vfData,form);
	}
	
	public BillWaterElectricBO findById(Long id) {
		return billWaterElectricDao.findById(id).orElse(null);
	}

	 @Transactional
	public void saveOrUpdate(BillWaterElectricBO bo) {
		 billWaterElectricDao.save(bo);
	}
	
	
	public void delete(BillWaterElectricBO bo) {
		billWaterElectricDao.deleteById(bo.getId());
		
	}

	public  List<ChartBean> totalByYear(Long year, Long type) {
	    return billWaterElectricDao.totalByYear(vfData, year, type);
	}
	
}
