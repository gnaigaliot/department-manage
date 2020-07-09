package com.example.employee.controller;

import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.PositionBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.PositionBO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.PositionForm;
import com.example.employee.service.DepartmentService;
import com.example.employee.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {
    @Autowired
    private PositionService positionService;
    // API get all        http://localhost:8085/positions/
    @GetMapping(path = "/")
    public @ResponseBody List<PositionBO> search(HttpServletRequest req){
        return positionService.getAll();
    }

    // API search        http://localhost:8085/positions/search
    @GetMapping(path = "/search")
    public @ResponseBody
    DataTableResults<PositionBean> search(HttpServletRequest req, PositionForm form){
        return positionService.search(form);
    }

    // API GET_BY_ID     http://localhost:8085/departments/search
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Response getById(HttpServletRequest req, @PathVariable Long id){
        PositionBO positionBO = positionService.getById(id);
        if (positionBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_NOT_EXISTED);
        }
        return Response.success().withData(positionBO);
    }

    // API: UPDATE OR CREATE Department
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody PositionForm form) throws Exception {
        System.out.println("// API: UPDATE OR CREATE Department");
        Long id = CommonUtil.NVL(form.getId());
        PositionBO positionBO;
        if(id > 0L) {
            positionBO = positionService.getById(id);
            if(positionBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            positionBO = new PositionBO();
            positionBO.setCreatedBy("admin");
            positionBO.setCreatedDate(new Date());
        }
        positionBO.setName(form.getName());
        positionBO.setCode(form.getCode());
        positionBO.setEditedBy("admin");
        positionBO.setEditedDate(new Date());
        positionBO.setStatus(1);
        positionBO.setSalary(form.getSalary());

        positionService.saveOrUpdate(positionBO);

        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(positionBO);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long id){

        PositionBO positionBO = positionService.getById(id);
        if (positionBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_NOT_EXISTED);
        }
        positionService.deleteById(positionBO.getId());
        return Response.success().withData(positionBO);
    }
}
