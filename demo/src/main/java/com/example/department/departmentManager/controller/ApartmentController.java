/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */
package com.example.department.departmentManager.controller;

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

import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.controller.BaseController;
import com.example.department.departmentManager.bean.ApartmentBean;
import com.example.department.departmentManager.bo.ApartmentBO;
import com.example.department.departmentManager.bo.ApartmentDetailBO;
import com.example.department.departmentManager.form.ApartmentForm;
import com.example.department.departmentManager.form.FormInfoDepartment;
import com.example.department.departmentManager.service.ApartmentDetailService;
import com.example.department.departmentManager.service.ApartmentService;
import com.example.department.personManager.service.PersonService;
import com.example.user.service.JwtService;

/**
 * @author squadX
 * @since 16/12/2019
 * @version 1.0
 */
@Controller
@RequestMapping("/department-manager/department")
public class ApartmentController extends BaseController {

    private static String adResouceKey = "resource.apartment";

    @Autowired
    private ApartmentService apartmentService;
    
    @Autowired
    private ApartmentDetailService apartmentDetailService;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private JwtService jwtService;


    /**
     * findById
     * @param apartmentId
     * @return
     * @throws Exception 
     */
    @GetMapping(path = "/{apartmentId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long apartmentId) throws Exception {
 
    	FormInfoDepartment formInfo = new FormInfoDepartment();
        ApartmentBO apartmentBO = apartmentService.findById(apartmentId);
        List<ApartmentDetailBO> lstApartDetailBO = apartmentDetailService.findByIdApartment(apartmentId);
        if(apartmentBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        formInfo.setApartmentForm(apartmentBO);
        formInfo.setLstApartmentForm(lstApartDetailBO);
        formInfo.setLstPersonIn(personService.findByPersonInDepartId(apartmentId));
        return Response.success().withData(formInfo);
    }

    /**
     * processSearch
     * @param ApartmentForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<ApartmentBean> processSearch(HttpServletRequest req, ApartmentForm form) {
        return apartmentService.getDatatables(form);
    }

    /**
     * saveOrUpdate ApartmentBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody FormInfoDepartment formInfo) throws Exception {
        
    	ApartmentBO form = formInfo.getApartmentForm();
    	List<ApartmentDetailBO> lstDetail = formInfo.getLstApartmentForm();
    	Long id = CommonUtil.NVL(form.getId());
        ApartmentBO apartmentBO;
        if(id > 0L) {
            apartmentBO = apartmentService.findById(id);
            if(apartmentBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            apartmentBO.setEditedDate(new Date());
            apartmentBO.setEditedBy(jwtService.getUsernameFromRequest(req));
        } else {
            apartmentBO = new ApartmentBO();
            apartmentBO.setCreatedDate(new Date());
            apartmentBO.setCreatedBy(jwtService.getUsernameFromRequest(req));
        }
        // check trung code
        if(apartmentService.checkDuplicateCode(CommonUtil.NVL(form.getCode()), id)) {
        	return new Response(Constants.RESPONSE_TYPE.WARNING, Constants.RESPONSE_CODE.CODE_USED, null);
        };
        // check trung nguoi trong apart
        if(apartmentService.duplicatePersonInApart(lstDetail)) {
        	return new Response(Constants.RESPONSE_TYPE.WARNING, Constants.RESPONSE_CODE.APART_HAVE_PERSON, null);
        }
        // check trung chu nha
        if(apartmentService.duplicateChuNha(lstDetail)) {
        	return new Response(Constants.RESPONSE_TYPE.WARNING, "Không được có trên 2 chủ nhà.", null);
        }
        apartmentBO.setCode(form.getCode());
        apartmentBO.setName(form.getName());
        apartmentBO.setPrice(form.getPrice());
        apartmentBO.setArea(form.getArea());
        apartmentBO.setDescription(form.getDescription());
        apartmentBO.setStatus(form.getStatus());
        apartmentService.saveOrUpdate(apartmentBO);
        // set gia tri nguoi thuoc can ho bao bang detail
        if(!CommonUtil.isNullOrEmpty(lstDetail)) {
        	// xoa gia tri cu~ trong truong hop update
        	if(form.getId() != null) {
        		apartmentDetailService.deleteByIdApartment(form.getId());
        	}
        	// them gia tri moi
        	for (ApartmentDetailBO apartmentDetailBO : lstDetail) {
        		if(apartmentDetailBO.getIdPerson() == null) {
            		continue;
            	}
        		apartmentDetailBO.setIdApartment(apartmentBO.getId());
        		apartmentDetailService.saveOrUpdate(apartmentDetailBO);
			}
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(null);
    }

    /**
     * delete
     * @param apartmentId
     * @return
     * @throws Exception 
     */
    @DeleteMapping(path = "/{apartmentId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long apartmentId) throws Exception {
        ApartmentBO bo ;
        if(apartmentId > 0L) {
            bo = apartmentService.findById(apartmentId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            if(apartmentService.apartHavePerson(apartmentId)) {
            	return Response.warning(Constants.RESPONSE_CODE.APART_HAVE_PERSON);
            }
            apartmentDetailService.deleteByIdApartment(bo.getId());
            apartmentService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @GetMapping(path = "/get-all-apartment")
    public @ResponseBody Response getAllApartment(HttpServletRequest req) {
    	return Response.success().withData(apartmentService.getAllApartment());
    }
}
