package com.example.employee.dao;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.employee.bean.DepartmentBean;
import com.example.employee.bean.PositionBean;
import com.example.employee.bo.DepartmentBO;
import com.example.employee.bo.PositionBO;
import com.example.employee.form.DepartmentForm;
import com.example.employee.form.PositionForm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface PositionDAO extends CrudRepository<PositionBO, Long> {
    @Query(value = "select * from positions p", nativeQuery = true)
    public List<PositionBO> findAllPosition();

    @Query( value= "select * from positions p where id = :id", nativeQuery = true)
    public PositionBO findPositionById(@Param("id") Long id);

    // For search
    public default DataTableResults<PositionBean> search(UttData vfData, PositionForm positionForm) {
        List<Object> paramList = new ArrayList<>();
        String sql = " SELECT ";
        sql += "        id As id           ";
        sql += "       ,code As code         ";
        sql += "       ,name As name         ";
        sql += "       ,salary As salary         ";
        sql += "       ,status As status         ";
        sql += "       ,created_by As createdBy      ";
        sql += "       ,edited_by As editedBy ";
        sql += "       ,created_date As createdDate ";
        sql += "       ,edited_date As editedDate ";
        sql += "       FROM positions ";

        StringBuilder strCondition = new StringBuilder(" WHERE status = 1 ");

        CommonUtil.filter(positionForm.getId(), strCondition, paramList, "id");
        CommonUtil.filter(positionForm.getCode(), strCondition, paramList, "code");
        CommonUtil.filter(positionForm.getName(), strCondition, paramList, "name");
        CommonUtil.filter(positionForm.getSalary(), strCondition, paramList, "salary");

        String selectFields = " order by id ";

        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, PositionBean.class);
    }

    // For delete
    @Modifying
    @Query( value= "update positions p set status=0 where id = :positionId", nativeQuery = true)
    void deleteById(@Param("positionId") Long positionId);
}