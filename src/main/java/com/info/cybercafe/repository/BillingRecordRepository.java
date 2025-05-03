package com.info.cybercafe.repository;

import com.info.cybercafe.entity.BillingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRecordRepository extends JpaRepository<BillingRecord, Long> {
}

