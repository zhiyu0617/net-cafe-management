package com.info.cybercafe.repository;

import com.info.cybercafe.entity.RechargeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RechargeTransactionRepository extends JpaRepository<RechargeTransaction, Long> {
    List<RechargeTransaction> findByUserId(Long userId);  // 查找用户的充值记录
}

