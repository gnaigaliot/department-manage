/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */
package com.example.department.personManager.controller;

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
import com.example.department.personManager.bean.PersonBean;
import com.example.department.personManager.form.PersonForm;
import com.example.department.personManager.bo.PersonBO;
import com.example.department.personManager.service.PersonService;
import com.example.user.service.JwtService;

/**
 * @author squadX
 * @since 14/12/2019
 * @version 1.0
 */
@Controller
@RequestMapping("/department-manager/person")
public class PersonController extends BaseController {
    @Autowired
    private PersonService personService;
    @Autowired
    private JwtService jwtService;
    /**
     * findById
     * @param personId
     * @return
     */
    @GetMapping(path = "/{personId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long personId) {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            return Response.invalidPermission();
//        }
        PersonBO personBO = personService.findById(personId);
        if(personBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(personBO);
    }

    /**
     * processSearch
     * @param PersonForm form
     * @return DataTableResults
     * @throws Exception 
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<PersonBean> processSearch(HttpServletRequest req, PersonForm form) throws Exception {
//        if (! permissionChecker.hasPermission("action.view", adResouceKey, req)) {
//            throw new PermissionException();
//        }
        return personService.getDatatables(form);
    }

    /**
     * saveOrUpdate PersonBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody PersonForm form) throws Exception {
        Long id = CommonUtil.NVL(form.getId());
        PersonBO personBO;
        if(id > 0L) {
            personBO = personService.findById(id);
            if(personBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            personBO = new PersonBO();
        }
        personBO.setCode(form.getCode());
        personBO.setName(form.getName());
        personBO.setGender(form.getGender());
        personBO.setAddress(form.getAddress());
        personBO.setIdentityNumber(form.getIdentityNumber());
        personBO.setDateOfBirth(form.getDateOfBirth());
        personBO.setPhoneNumber(form.getPhoneNumber());
        personBO.setEmail(form.getEmail());
        personService.saveOrUpdate(personBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(personBO);
    }

    /**
     * delete
     * @param personId
     * @return
     */
    @DeleteMapping(path = "/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long personId) {
        PersonBO bo ;
        if(personId > 0L) {
            bo = personService.findById(personId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            if(personService.personHaveApart(personId)) {
            	return new Response(Constants.RESPONSE_TYPE.WARNING, Constants.RESPONSE_CODE.PERSON_HAVE_APART, null);
            }
            personService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @GetMapping(path = "/find-autocomplete/{codeOrName}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable String codeOrName) {
        List<PersonBO> lstPersonBO = personService.findByCodeOrName(codeOrName);
        return Response.success().withData(lstPersonBO);
    }
}
