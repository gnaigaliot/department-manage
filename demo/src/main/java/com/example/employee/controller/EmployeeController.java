package com.example.employee.controller;
import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;

import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.EmployeeBO;
import com.example.employee.bo.WorkProcessBO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.EmployeeForm;
import com.example.employee.service.EmployeeService;

import com.example.employee.service.WorkProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WorkProcessService workProcessService;
    /**
     * API GET http://localhost:8085/departments/
     * Description: get all employee without pagination
     * @param req
     * @return
     */
    @GetMapping(path = "/")
    public @ResponseBody
    List<EmployeeBO> getAll(HttpServletRequest req){
        return employeeService.getAll();
    }

    /**
     * API GET http://localhost:8085/departments/search
     * Description: search employee in db
     * @param req
     * @param form
     * @return
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<EmployeeBean> search(HttpServletRequest req, EmployeeForm form){
        return employeeService.search(form);
    }

    /**
     * API GET http://localhost:8085/departments/1
     * Description: get full information of employee by id
     * @param req
     * @param idEmployee
     * @return
     */
    @GetMapping(path = "/{idEmployee}")
    public @ResponseBody Response getById(HttpServletRequest req, @PathVariable Long idEmployee){
        List<EmployeeBean> employeeBeanList = employeeService.getEmployeeBeanById(idEmployee);
        if (employeeBeanList.size() == 0 || employeeBeanList == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_NOT_EXISTED);
        }
        return Response.success().withData(employeeBeanList.get(0));
    }

    /**
     * API DELETE http://localhost:8085/departments/1
     * Description: Delete employee by id
     * @param req
     * @param idEmployee
     * @return
     */
    @DeleteMapping(path = "/{idEmployee}")
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long idEmployee){
        EmployeeBO employeeBO = employeeService.getEmployeeBOById(idEmployee);
        if (employeeBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_NOT_EXISTED);
        }
        employeeService.deleteById(employeeBO.getId());
        return Response.success().withData(employeeBO);
    }

    /**
     * API PUT or POST
     * Description: update or create new Employee
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody EmployeeForm form) throws Exception {
        Long idEmployee = form.getId();
        EmployeeBO employeeBO;
        WorkProcessBO workProcessBO;
        System.out.println(form.toString());
        // Save Employee
        if(idEmployee != null && idEmployee > 0L) {
            // Update
            employeeBO = employeeService.getEmployeeBOById(idEmployee);
            workProcessBO = workProcessService.getWorkProcessByEmployeeId(idEmployee);
            if(employeeBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {

            // Created
            employeeBO = new EmployeeBO();
            workProcessBO = new WorkProcessBO();
            employeeBO.setCreatedBy("admin");
            employeeBO.setCreatedDate(new Date());
        }

        // Save employee
        employeeBO.setCode(form.getCode());
        employeeBO.setName(form.getName());
//        employeeBO.setEditedBy(CommonUtil.getUserLoginName(req));
        employeeBO.setEditedBy("admin");
        employeeBO.setEditedDate(new Date());
        employeeBO.setDateOfBird(form.getDateOfBird());
        employeeBO.setGender(form.getGender());
        employeeBO.setEmail(form.getEmail());
        employeeBO.setPhonenumber(form.getPhonenumber());
        employeeBO.setStatus(1);
        employeeBO.setIsWorking(1);
        employeeService.saveOrUpdate(employeeBO);
        if (idEmployee == null) {
            String newCode = "EMP";
            newCode = newCode + Long.toString(employeeBO.getId());
            employeeBO.setCode(newCode);
            employeeService.saveOrUpdate(employeeBO);
        }
        // Save WorkProcess
        workProcessBO.setIdEmployee(employeeBO.getId());
        workProcessBO.setIdDepartment(form.getIdDepartment());
        workProcessBO.setIdPositions(form.getIdPositions());
        workProcessBO.setStartDate(form.getStartDate());
        workProcessBO.setEndDate(form.getEndDate());
        workProcessService.saveOrUpdate(workProcessBO);

        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(employeeBO);
    }

}
