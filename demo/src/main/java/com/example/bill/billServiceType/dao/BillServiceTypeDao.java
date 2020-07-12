package com.example.bill.billServiceType.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bill.billServiceType.bean.BillServiceTypeBean;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.form.BillServiceTypeForm;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.common.UttData;

@Transactional
@Repository
public interface BillServiceTypeDao extends JpaRepository<BillServiceTypeBO, Long> {

	public default DataTableResults<BillServiceTypeBean> getDatatables(UttData vfData, BillServiceTypeForm formData) {
        List<Object> paramList = new ArrayList<>();
	        String sql =	"SELECT    " 
			        	+	"    b.id AS id,   " 
			        	+	"    b.code AS code,   " 
			        	+	"    b.name AS name,   " 
			        	+	"    b.price AS price,   " 
			        	+	"    b.created_by AS createdBy,   " 
			        	+	"    b.created_date AS createdDate,   "
			        	+	"    b.edited_by AS editedBy,   " 
			        	+	"    b.edited_date AS editedDate,   " 
			        	+	"    b.unit AS unit   " 
			        	+	"FROM   " 
			        	+	"    bill_service_type AS b   " ;

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");

        CommonUtil.filter(formData.getCode(), strCondition, paramList, "code");
        CommonUtil.filter(formData.getName(), strCondition, paramList, "name");
        

        String selectFields = " order by createdDate DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, BillServiceTypeBean.class);
    }
	@Query("From BillServiceTypeBO as b where b.isService =:isService ")
	List<BillServiceTypeBO> findByIsService(@Param("isService") Long isService ) ;
	
	Optional<BillServiceTypeBO> findByCode(String code);
}
