package com.info.cybercafe.service;

import com.info.cybercafe.entity.BillingRecord;
import com.info.cybercafe.entity.ComputerSession;
import com.info.cybercafe.repository.BillingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingRecordService {
    @Autowired
    private BillingRecordRepository billingRecordRepository;

    // 生成计费记录
    public BillingRecord generateBill(ComputerSession session, double ratePerHour) {
        long sessionDurationMinutes = Duration.between(session.getStartTime(), session.getEndTime()).toMinutes();
        double amount = (sessionDurationMinutes / 60.0) * ratePerHour;

        BillingRecord record = new BillingRecord();
        record.setComputerSession(session);
        record.setAmount(amount);
        record.setBillingTime(LocalDateTime.now());

        return billingRecordRepository.save(record);
    }

    // 获取所有计费记录
    public List<BillingRecord> getAllBillingRecords() {
        return billingRecordRepository.findAll();
    }
}