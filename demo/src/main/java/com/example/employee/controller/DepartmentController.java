package com.example.employee.controller;

import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;

import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.EmployeeBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.PositionBO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.EmployeeForm;
import com.example.employee.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.common.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // API get all        http://localhost:8085/departments/
    @GetMapping(path = "/")
    public @ResponseBody List<DepartmentBO> search(HttpServletRequest req){
        return departmentService.getAll();
    }

    // API search        http://localhost:8085/departments/search
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<DepartmentBean> search(HttpServletRequest req, DepartmentForm form){
        return departmentService.search(form);
    }

    @GetMapping(path = "/{idDepartment}/employees")
    public @ResponseBody
    List<EmployeeBean> getListEmployee(HttpServletRequest req, @PathVariable Long idDepartment){
        List<EmployeeBean> employeeBeanList = departmentService.getEmployeeBeanListById(idDepartment);
        return employeeBeanList;
    }

    // API GET_BY_ID     http://localhost:8085/departments/1
    @GetMapping(path = "/{department_id}")
    public @ResponseBody Response getById(HttpServletRequest req, @PathVariable Long department_id){
        DepartmentBO departmentBO = departmentService.getById(department_id);
        if (departmentBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_NOT_EXISTED);
        }
        return Response.success().withData(departmentBO);
    }

    // API: Cập nhât và thêm mới một phòng ban
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody DepartmentForm form) throws Exception {
        Long id = CommonUtil.NVL(form.getId());
        DepartmentBO departmentBO;
        if(id > 0L) {
            departmentBO = departmentService.getById(id);
            if(departmentBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            departmentBO = new DepartmentBO();
            departmentBO.setCreatedBy("admin");
            departmentBO.setCreatedDate(new Date());
        }

        departmentBO.setCode(form.getCode());
        departmentBO.setName(form.getName());
        departmentBO.setEditedBy("admin");
        departmentBO.setEditedDate(new Date());
        departmentBO.setStatus(1);
        departmentService.saveOrUpdate(departmentBO);

        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(departmentBO);
    }


    // Xóa một phòng ban
    @DeleteMapping(path = "/{department_id}")
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long department_id){
        DepartmentBO departmentBO = departmentService.getById(department_id);
        if (departmentBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_NOT_EXISTED);
        }
        departmentService.deleteById(departmentBO.getId());
        return Response.success().withData(departmentBO);
    }

    // Lấy ra danh sách nhân viên của phòng ban
    @GetMapping(path = "/employees/{department_id}")
    public @ResponseBody DataTableResults<EmployeeBean> getEmployeesOfDepartmentByDepartmentId(HttpServletRequest req,
                                                                                               @PathVariable Long department_id,
                                                                                               EmployeeForm employeeForm){
        return departmentService.getEmployeesOfDepartmentByDepartmentId(department_id, employeeForm);
    }

}
