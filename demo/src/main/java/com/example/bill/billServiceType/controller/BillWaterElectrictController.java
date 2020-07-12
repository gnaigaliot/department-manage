package com.example.bill.billServiceType.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.bill.billServiceType.bean.BillWaterElectricBean;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.bo.BillWaterElectricBO;
import com.example.bill.billServiceType.dao.BillServiceTypeDao;
import com.example.bill.billServiceType.dao.BillWaterElectricDao;
import com.example.bill.billServiceType.form.BillServiceTypeForm;
import com.example.bill.billServiceType.form.BillWaterElectricForm;
import com.example.bill.billServiceType.service.BillWaterElectricService;
import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;

@Controller
@RequestMapping("/bill/bill-water-electrict")
public class BillWaterElectrictController {
	@Autowired
	private BillWaterElectricService billWaterElectricService;
	
	@Autowired 
	private BillServiceTypeDao billServiceTypeDao;
	@GetMapping(path = "/search")
	public @ResponseBody DataTableResults<BillWaterElectricBean> processSearch(HttpServletRequest req,
			BillWaterElectricForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            throw new PermissionException();
//        }
		return billWaterElectricService.getDatatables(form);
	}
	@GetMapping(path = "/{id}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long id) {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            return Response.invalidPermission();
//        }
		BillWaterElectricBO billWaterElectricBO = billWaterElectricService.findById(id);
        if(billWaterElectricBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(billWaterElectricBO);
    }
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody BillWaterElectricForm form)
			throws Exception {
		Long id = CommonUtil.NVL(form.getId());
		
		BillWaterElectricBO bo = new BillWaterElectricBO();
		if (id > 0L) {
			bo = billWaterElectricService.findById(id);
			if (bo == null) {
				return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
			}
			bo.setEditedBy(CommonUtil.getUserLoginName(req));
			bo.setEditeDate(new Date());
		} else {
			bo.setCreatedDate(new Date());
		//	bo.setCreatedBy(CommonUtil.getUserLoginName(req));
		}
		
		bo.setNewNumber(form.getNewNumber());
		bo.setOldNumber(form.getOldNumber());
		bo.setAmount(form.getAmount());
		bo.setTotalPrice(form.getTotalPrice());
		bo.setIdApartment(form.getIdApartment());
		bo.setIdBillType(form.getType());
		bo.setFromDate(form.getFromDate());
		bo.setToDate(form.getToDate());
		billWaterElectricService.saveOrUpdate(bo);
		bo.setCode("HDDN"+bo.getId());
		billWaterElectricService.saveOrUpdate(bo);
		return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(bo);
	}
	
	@GetMapping(path="/unit")
	public @ResponseBody List<BillServiceTypeBO> priceUnit(HttpServletRequest req)
			throws Exception{
		List<BillServiceTypeBO> lst= billServiceTypeDao.findByIsService(0L);
		return lst;
	}
	
	@DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long id) {
	 BillWaterElectricBO bo = new BillWaterElectricBO();
        if(id > 0L) {
            bo = billWaterElectricService.findById(id);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            billWaterElectricService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
	
}
