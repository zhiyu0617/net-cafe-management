package com.info.cybercafe.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "computer-session")
    private List<ComputerSession> sessions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ComputerSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<ComputerSession> sessions) {
        this.sessions = sessions;
    }

}
