package com.info.cybercafe.service;

import com.info.cybercafe.entity.SystemLog;
import com.info.cybercafe.repository.SystemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {
    @Autowired
    private SystemLogRepository systemLogRepository;

    // 创建系统日志
    public SystemLog createLog(String event) {
        SystemLog log = new SystemLog();
        log.setEvent(event);
        log.setTimestamp(java.time.LocalDateTime.now());
        return systemLogRepository.save(log);
    }
}
