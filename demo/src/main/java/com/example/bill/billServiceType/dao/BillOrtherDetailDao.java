package com.example.bill.billServiceType.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.bill.billServiceType.bo.BillOrtherDetailBo;

public interface BillOrtherDetailDao extends JpaRepository<BillOrtherDetailBo, Long>{
    @Modifying
    @Query("delete from BillOrtherDetailBo u where u.idBillOrther = ?1")
    void deleteByIdBill(Long id);

    List<BillOrtherDetailBo> findByIdBillOrther(Long idBillOrther);
    
}
