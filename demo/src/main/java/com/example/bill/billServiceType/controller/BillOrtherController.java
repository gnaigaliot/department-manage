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

import com.example.bill.billServiceType.bean.BillOrtherBean;
import com.example.bill.billServiceType.bo.BillOrtherBo;
import com.example.bill.billServiceType.bo.BillOrtherDetailBo;
import com.example.bill.billServiceType.bo.BillWaterElectricBO;
import com.example.bill.billServiceType.form.BillInputForm;
import com.example.bill.billServiceType.form.BillOrtherForm;
import com.example.bill.billServiceType.service.BillOrtherService;
import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.service.ApartmentService;

@Controller
@RequestMapping("/bill/bill-orther")
public class BillOrtherController {
    @Autowired 
    private BillOrtherService billOrtherService;
    
    @Autowired 
    private ApartmentService apartmentService;
    
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<BillOrtherBean> processSearch(HttpServletRequest req, BillOrtherForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            throw new PermissionException();
//        }
        return billOrtherService.getDatatables(form);
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long id) throws Exception {
 
        BillInputForm formInfo = new BillInputForm();
        BillOrtherBo billOrtherBo = billOrtherService.findById(id);
        List<BillOrtherDetailBo> listBillOrther = billOrtherService.findByIdDetail(id);
        formInfo.setBillOrtherBo(billOrtherBo);
        formInfo.setListBillOrther(listBillOrther);
        return Response.success().withData(formInfo);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody BillInputForm form) throws Exception {
        List<BillOrtherDetailBo> listBillOrther = form.getListBillOrther();
        BillOrtherBo billOrtherForm= form.getBillOrtherBo();
        
        Long id = CommonUtil.NVL(billOrtherForm.getId());
        BillOrtherBo billOrtherBo = new BillOrtherBo();
        
        if(id > 0L) {
            billOrtherBo = billOrtherService.findById(id);
            billOrtherBo.setEditedDate(new Date());
            //billOrtherBo.setEditeeddBy(CommonUtil.getUserLoginName(req));
            billOrtherService.deleteByIdBill(billOrtherBo);
        } else {
            billOrtherBo.setCreatedDate(new Date());
            //billOrtherBo.setCreateddBy(CommonUtil.getUserLoginName(req));
        }
        billOrtherBo.setCodeApartment(billOrtherForm.getCodeApartment());
        billOrtherBo.setIdApartment(billOrtherForm.getIdApartment());
        billOrtherBo.setTotalPrice(billOrtherForm.getTotalPrice());
        billOrtherBo.setFromDate(billOrtherForm.getFromDate());
        billOrtherBo.setToDate(billOrtherForm.getToDate());
        billOrtherService.saveOrUpdate(billOrtherBo);
        billOrtherBo.setCode("HD"+ billOrtherBo.getId());
        
        for (BillOrtherDetailBo item : listBillOrther) {
            BillOrtherDetailBo bo = new BillOrtherDetailBo();
            bo.setIdBillOrther(billOrtherBo.getId());
            bo.setAmount(item.getAmount());
            bo.setPrice(item.getPrice());
            bo.setIdType(item.getIdType());
            bo.setUnit(item.getUnit());
            billOrtherService.saveBillOrtheretail(bo);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(billOrtherBo);
    }
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long id) {
        BillOrtherBo bo = new BillOrtherBo();
        if(id > 0L) {
            bo = billOrtherService.findById(id);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            billOrtherService.delete(bo);
            billOrtherService.deleteByIdBill(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
}
