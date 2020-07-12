package com.example.bill.billServiceType.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bill.billServiceType.bean.BillOrtherBean;
import com.example.bill.billServiceType.bean.BillServiceTypeBean;
import com.example.bill.billServiceType.bo.BillOrtherBo;
import com.example.bill.billServiceType.form.BillOrtherForm;
import com.example.bill.billServiceType.form.BillServiceTypeForm;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;

public interface BillOrtherDao extends JpaRepository<BillOrtherBo, Long> {
	
	public default DataTableResults<BillOrtherBean> getDatatables(UttData vfData, BillOrtherForm form) {
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

//        CommonUtil.filter(form.getCode(), strCondition, paramList, "code");
//        CommonUtil.filter(form.getName(), strCondition, paramList, "name");
//        

        String selectFields = " order by createdDate DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, BillOrtherBean.class);
    }
}
