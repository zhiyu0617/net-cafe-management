package com.info.cybercafe.controller;

import com.info.cybercafe.entity.ComputerSession;
import com.info.cybercafe.service.ComputerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:3000")
public class ComputerSessionController {
    @Autowired
    private ComputerSessionService sessionService;

    // 启动会话
    @PostMapping("/start")
    public ResponseEntity<ComputerSession> startSession(@RequestParam Long userId, @RequestParam Long computerId) {
        return ResponseEntity.ok(sessionService.startSession(userId, computerId));
    }

    @PostMapping("/stop")
    public ResponseEntity<ComputerSession> stopSession(@RequestParam Long userId) {
        ComputerSession stopped = sessionService.stopSession(userId);
        return (stopped != null) ? ResponseEntity.ok(stopped) : ResponseEntity.notFound().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<ComputerSession>> getActiveSessions() {
        return ResponseEntity.ok(sessionService.getActiveSessions());
    }

    @GetMapping("/active/by-user")
    public ResponseEntity<List<ComputerSession>> getActiveSessionsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(sessionService.getActiveSessionsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<ComputerSession>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }


}