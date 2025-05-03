package com.info.cybercafe.controller;

import com.info.cybercafe.entity.SystemLog;
import com.info.cybercafe.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class SystemLogController {
    @Autowired
    private SystemLogService systemLogService;

    // 创建系统日志
    @PostMapping
    public ResponseEntity<SystemLog> createLog(@RequestBody String event) {
        return ResponseEntity.ok(systemLogService.createLog(event));
    }
}
