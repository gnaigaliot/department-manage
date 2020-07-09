package com.example.bill.billServiceType.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.bill.billServiceType.bean.BillServiceTypeBean;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.dao.BillServiceTypeDao;
import com.example.bill.billServiceType.form.BillServiceTypeForm;
import com.example.bill.billServiceType.service.BillServiceTypeService;
import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;


@Controller
@RequestMapping("/bill/bill-service-type")
public class BillServiceTypeController {
	@Autowired
	private BillServiceTypeService billServiceTypeService;
	 @Autowired 
    private BillServiceTypeDao billServiceTypeDao;
    
	
	@GetMapping(path = "/search")
    public @ResponseBody DataTableResults<BillServiceTypeBean> processSearch(HttpServletRequest req, BillServiceTypeForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            throw new PermissionException();
//        }
        return billServiceTypeService.getDatatables(form);
    }
	
	@GetMapping(path = "/{id}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long id) {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            return Response.invalidPermission();
//        }
		 BillServiceTypeBO billServiceTypeBO = billServiceTypeService.findById(id);
        if(billServiceTypeBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(billServiceTypeBO);
    }

	 @PostMapping()
	 @ResponseStatus(HttpStatus.CREATED)
	 public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody BillServiceTypeForm form) throws Exception {
        Long id = CommonUtil.NVL(form.getId());
        BillServiceTypeBO billServiceTypeBO = new BillServiceTypeBO();
        if(id > 0L) {
        	billServiceTypeBO = billServiceTypeService.findById(id); 
            if(billServiceTypeBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            billServiceTypeBO.setEditeeddBy("Edit");
            billServiceTypeBO.setEditedDate(new Date());
        } else {
        	billServiceTypeBO = new BillServiceTypeBO();
        	billServiceTypeBO.setCreatedDate(new Date());
            billServiceTypeBO.setCreateddBy("create");
        }
        BillServiceTypeBO bo = billServiceTypeService.findByCode(form.getCode());
        if(bo != null && ( billServiceTypeBO == null || !bo.getId().equals(billServiceTypeBO.getId() ))) {
        	return Response.warning("Mã đã được sử dụng");
        }
        billServiceTypeBO.setCode(form.getCode());
        billServiceTypeBO.setName(form.getName());
        billServiceTypeBO.setPrice(form.getPrice());
        billServiceTypeBO.setUnit(form.getUnit());
        billServiceTypeBO.setIsService(1L);
        billServiceTypeService.saveOrUpdate(billServiceTypeBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(billServiceTypeBO);
    }
	 
	 @DeleteMapping(path = "/{id}")
	    @ResponseStatus(HttpStatus.OK)
	    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long id) {
		 BillServiceTypeBO bo = new BillServiceTypeBO();
	        if(id > 0L) {
	            bo = billServiceTypeService.findById(id);
	            if(bo == null) {
	                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
	            }
	            billServiceTypeService.delete(bo);
	            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
	        } else {
	            return Response.error(Constants.RESPONSE_CODE.ERROR);
	        }
	    }
	 @GetMapping(path="/unit")
	    public @ResponseBody List<BillServiceTypeBO> priceUnit(HttpServletRequest req)
	            throws Exception{
	        List<BillServiceTypeBO> lst= billServiceTypeDao.findByIsService(1L);
	        return lst;
	    }
	 
	 
}
