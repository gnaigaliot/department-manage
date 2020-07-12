package com.example.bill.billServiceType.form;

import java.util.List;

import com.example.bill.billServiceType.bo.BillOrtherBo;
import com.example.bill.billServiceType.bo.BillOrtherDetailBo;

public class BillInputForm {
    private List<BillOrtherDetailBo> listBillOrther;
    private BillOrtherBo billOrtherBo;
    
    public List<BillOrtherDetailBo> getListBillOrther() {
        return listBillOrther;
    }
    
    public void setListBillOrther(List<BillOrtherDetailBo> listBillOrther) {
        this.listBillOrther = listBillOrther;
    }
    
    public BillOrtherBo getBillOrtherBo() {
        return billOrtherBo;
    } 
    
    public void setBillOrtherBo(BillOrtherBo billOrtherBo) {
        this.billOrtherBo = billOrtherBo;
    }
    
    
    
}
