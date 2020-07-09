package com.example.bill.billServiceType.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bill.billServiceType.bean.BillOrtherBean;
import com.example.bill.billServiceType.bean.BillWaterElectricBean;
import com.example.bill.billServiceType.bo.BillOrtherBo;
import com.example.bill.billServiceType.bo.BillOrtherDetailBo;
import com.example.bill.billServiceType.bo.BillServiceTypeBO;
import com.example.bill.billServiceType.dao.BillOrtherDao;
import com.example.bill.billServiceType.dao.BillOrtherDetailDao;
import com.example.bill.billServiceType.form.BillOrtherDetailForm;
import com.example.bill.billServiceType.form.BillOrtherForm;
import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.UttData;

@Service
public class BillOrtherService {
	@Autowired 
	private BillOrtherDao billOrtherDao;
	
	@Autowired 
    private BillOrtherDetailDao billOrtherDetailDao;
	
	@Autowired
    private UttData vfData;
	public DataTableResults<BillOrtherBean> getDatatables(BillOrtherForm form) {
	    List<Object> paramList = new ArrayList<>();
        String sql = " select "
                     + "    b.id as id,   "
                     + "    p.name as name "
                     + ",   b.code as code"
                     + ",   a.code  as codeApartment "
                     + ",   p.phone_number as numberPhone"
                     + ",   b.total_price as totalPrice   " + 
                     "  FROM bill_service b join apartment a on b.id_apartment = a.id     " + 
                     "    join apartment_detail ad on a.id= ad.id_apartment    " + 
                     "    join person p on p.id = ad.id_person    " ;
    StringBuilder strCondition = new StringBuilder(" WHERE ad.is_main = 1 ");
    CommonUtil.filter(form.getCode(), strCondition,paramList, "b.code");
    CommonUtil.filter(form.getIdApartment(), strCondition,paramList, "a.id");
//      CommonUtil.filter(form.getType(), strCondition, paramList, "id_bill_type");
//      CommonUtil.filter(form.getCodeBill(), strCondition, paramList, "b.code");
//      CommonUtil.filter(form.getCodeApartment(), strCondition, paramList, "a.code");
//      CommonUtil.filter(form.getFormDate(), strCondition, paramList, "formDate");
//    CommonUtil.filter(form.type(), strCondition, paramList, "endDate");

    String selectFields = " order by b.id DESC "; 
    return vfData.findPaginationQuery(sql + strCondition.toString(), selectFields, paramList, BillOrtherBean.class);

	}
	@Transactional
    public void saveOrUpdate(BillOrtherBo billOrtherBo) {
        billOrtherDao.save(billOrtherBo);
    }
    public void saveBillOrtheretail(BillOrtherDetailBo bo) {
        billOrtherDetailDao.save(bo);
    }
    @Transactional
    public void deleteByIdBill(BillOrtherBo billOrtherBo) {
        billOrtherDetailDao.deleteByIdBill(billOrtherBo.getId());
        
    }
    public BillOrtherBo findById(Long id) {
        return billOrtherDao.findById(id).orElse(null);
    }
    public List<BillOrtherDetailBo> findByIdDetail(Long id) {
        return billOrtherDetailDao.findByIdBillOrther(id);
    }
    public void delete(BillOrtherBo bo) {
         billOrtherDao.deleteById(bo.getId());
        
    }
  
}
