package com.info.cybercafe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BillingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ComputerSession computerSession;

    private double amount;
    private LocalDateTime billingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComputerSession getComputerSession() {
        return computerSession;
    }

    public void setComputerSession(ComputerSession computerSession) {
        this.computerSession = computerSession;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getBillingTime() {
        return billingTime;
    }

    public void setBillingTime(LocalDateTime billingTime) {
        this.billingTime = billingTime;
    }
}
