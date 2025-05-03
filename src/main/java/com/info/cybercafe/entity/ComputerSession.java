package com.info.cybercafe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ComputerSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"sessions"}) // 保留user字段，防止递归
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "computer_id", nullable = false)
    @JsonIgnoreProperties({"sessions"})
    private Computer computer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double charge;

    @Column(nullable = false)
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Computer getComputer() { return computer; }
    public void setComputer(Computer computer) { this.computer = computer; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getCharge() { return charge; }
    public void setCharge(Double charge) { this.charge = charge; }
}
