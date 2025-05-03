package com.info.cybercafe.service;

import com.info.cybercafe.entity.RechargeTransaction;
import com.info.cybercafe.repository.RechargeTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RechargeTransactionService {
    @Autowired
    private RechargeTransactionRepository rechargeTransactionRepository;

    // 创建充值记录
    public RechargeTransaction createTransaction(RechargeTransaction transaction) {
        transaction.setTimestamp(java.time.LocalDateTime.now());
        return rechargeTransactionRepository.save(transaction);
    }

    // 获取用户的所有充值记录
    public List<RechargeTransaction> getTransactionsByUser(Long userId) {
        return rechargeTransactionRepository.findByUserId(userId);
    }
}
