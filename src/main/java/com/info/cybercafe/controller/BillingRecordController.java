package com.info.cybercafe.controller;

import com.info.cybercafe.entity.BillingRecord;
import com.info.cybercafe.service.BillingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingRecordController {
    @Autowired
    private BillingRecordService billingRecordService;

    // 获取所有计费记录
    @GetMapping
    public ResponseEntity<List<BillingRecord>> getAllBillingRecords() {
        return ResponseEntity.ok(billingRecordService.getAllBillingRecords());
    }
}