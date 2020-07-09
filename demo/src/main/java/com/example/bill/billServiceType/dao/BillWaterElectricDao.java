package com.example.bill.billServiceType.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bill.billServiceType.bean.BillServiceTypeBean;
import com.example.bill.billServiceType.bean.BillWaterElectricBean;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.bo.BillWaterElectricBO;
import com.example.bill.billServiceType.form.BillWaterElectricForm;
import com.example.common.ChartBean;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;
import com.example.report.bean.StatisticalBean;
import com.example.report.form.statisticalForm;

public interface BillWaterElectricDao extends JpaRepository<BillWaterElectricBO, Long> {

	public default DataTableResults<BillWaterElectricBean> getDatatables(UttData vfData, BillWaterElectricForm form){
        List<Object> paramList = new ArrayList<>();
	        String sql = 	  " select "
			        		+ "		b.id "
			        		+ ",	b.code as codeBill"
			        		+ ",	a.code as codeApartment"
			        		+ ", 	p.name as personName"
			        		+ ", 	p.phone_number as numberPhone"
			        		+ ", 	b.total_price as totalPrice"
			        		+ ", 	b.status as status"
			        		+ ", 	b.id_bill_type as type"
			        		+ ",	b.created_date as createdDate "
			        		+ " from apartment a join apartment_detail ad on a.id= ad.id_apartment" 
			        		+ "					 join person p on p.id = ad.id_person" 
			        		+ "             	 join bill_water_electric b on b.id_apartment = a.id  "    ;

        StringBuilder strCondition = new StringBuilder(" WHERE ad.is_main = 1  ");

		  CommonUtil.filter(form.getType(), strCondition, paramList, "id_bill_type");
		  CommonUtil.filter(form.getCodeBill(), strCondition, paramList, "b.code");
		  CommonUtil.filter(form.getIdApartment(), strCondition, paramList, "a.id");
		  CommonUtil.filter(form.getFromDate(), strCondition, paramList, "formDate");
//		  CommonUtil.filter(form.type(), strCondition, paramList, "endDate");

        String selectFields = " order by b.id DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, BillWaterElectricBean.class);
    }

	public default List<ChartBean> totalByYear(UttData uttData, Long year, Long type) {
        String hql = "  with temp as ( " + 
                "    select b.month, sum(total_price) as data " + 
                "    from bill_water_electric b " + 
                "    where year = :year and id_bill_type = :type" + 
                "    group by month " + 
                " ) " + 
                " select m.month as label, ifnull(temp.data,0)  as data " + 
                " from v_month m " + 
                " left join temp on m.month = temp.month " + 
                " order by m.month asc " ;
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("year", year);
        query.setParameter("type", type);
        uttData.setResultTransformer(query, ChartBean.class);
        return query.list();
    }
	
	public default DataTableResults<StatisticalBean> search(UttData vfData, statisticalForm form){
        List<Object> paramList = new ArrayList<>();
	        String sql = 	  " select "
			        		+ "		b.month as thang"
			        		+ ",	b.year as nam"
			        		+ ",	count(b.id) as hoa_don_dien_nuoc"
			        		+ ", 	sum(b.total_price) as tong_tien"
			        		+ ", 	count(c.id) as hoa_don_dich_vu"
			        		+ ", 	sum(d.price) as tong_tien_dich_vu"
			        		+ " 	FROM "
			        		+ "		departmentmanager.bill_water_electric as b" 
			        		+ ",	departmentmanager.bill_service as c " 
			        		+ "     inner join departmentmanager.bill_service_detail as d"    
					        + "     on c.id = d.id_bill_service"    
					        + "     where b.month=c.month and c.year = b.year" ;

        StringBuilder strCondition = new StringBuilder(" and 1 = 1  ");

		  CommonUtil.filter(form.getMonth(), strCondition, paramList, "b.month");
		  CommonUtil.filter(form.getYear(), strCondition, paramList, "b.year");
//		  CommonUtil.filter(form.type(), strCondition, paramList, "endDate");

        String selectFields = "group by b.month order by b.month DESC ";
        return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, StatisticalBean.class);
    }
}
