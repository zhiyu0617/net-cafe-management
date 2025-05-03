package com.info.cybercafe.controller;

import com.info.cybercafe.entity.Computer;
import com.info.cybercafe.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @GetMapping("/all")
    public List<Computer> getAllComputers() {
        return computerService.getAllComputers();
    }

    // 获取状态为 "AVAILABLE" 的电脑
    @GetMapping("/available")
    public ResponseEntity<List<Computer>> getAvailableComputers() {
        List<Computer> availableComputers = computerService.getAvailableComputers();
        return ResponseEntity.ok(availableComputers);
    }

    // 根据 ID 获取特定的电脑
    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputerById(@PathVariable Long id) {
        Computer computer = computerService.getComputerById(id);
        return ResponseEntity.ok(computer);
    }
}