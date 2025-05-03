package com.info.cybercafe.controller;

import com.info.cybercafe.entity.RechargeTransaction;
import com.info.cybercafe.service.RechargeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharge")
public class RechargeTransactionController {
    @Autowired
    private RechargeTransactionService rechargeTransactionService;

    // 创建充值记录
    @PostMapping
    public ResponseEntity<RechargeTransaction> createTransaction(@RequestBody RechargeTransaction transaction) {
        return ResponseEntity.ok(rechargeTransactionService.createTransaction(transaction));
    }

    // 获取用户的充值记录
    @GetMapping("/{userId}")
    public ResponseEntity<List<RechargeTransaction>> getTransactionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(rechargeTransactionService.getTransactionsByUser(userId));
    }
}
