package com.info.cybercafe.service;

import com.info.cybercafe.entity.Computer;
import com.info.cybercafe.entity.ComputerSession;
import com.info.cybercafe.entity.User;
import com.info.cybercafe.repository.ComputerSessionRepository;
import com.info.cybercafe.repository.ComputerRepository;
import com.info.cybercafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComputerSessionService {

    private static final double HOURLY_RATE = 5.0;
    @Autowired
    private ComputerSessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComputerRepository computerRepository;

    public List<ComputerSession> getActiveSessions() {
        return sessionRepository.findAll().stream()
                .filter(s -> s.getEndTime() == null)
                .toList();
    }

    public List<ComputerSession> getActiveSessionsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<ComputerSession> sessions = sessionRepository.findByUserAndEndTimeIsNull(user);

        // 强制初始化关联的 computer
        sessions.forEach(session -> {
            if (session.getComputer() != null) {
                session.getComputer().getId(); // 强制调用触发加载
            }
        });

        return sessions;
    }

    // Start a new session
    public ComputerSession startSession(Long userId, Long computerId) {
        User user = userRepository.findById(userId).orElseThrow();
        Computer computer = computerRepository.findById(computerId).orElseThrow();

        if (!"AVAILABLE".equalsIgnoreCase(computer.getStatus())) {
            throw new RuntimeException("Computer is currently in use.");
        }

        computer.setStatus("IN_USE");
        computerRepository.save(computer);

        ComputerSession session = new ComputerSession();
        session.setUser(user);
        session.setComputer(computer);
        session.setStartTime(LocalDateTime.now());
        session.setStatus("ACTIVE");
        return sessionRepository.save(session);
    }

    // End a session
    public ComputerSession stopSession(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<ComputerSession> sessions = sessionRepository.findByUserAndEndTimeIsNull(user);

        if (!sessions.isEmpty()) {
            ComputerSession session = sessions.get(0);
            session.setEndTime(LocalDateTime.now());
            long minutes = Duration.between(session.getStartTime(), session.getEndTime()).toMinutes();
            double cost = (minutes / 60.0) * HOURLY_RATE;
            session.setCharge(cost);
            session.setStatus("FINISHED");

            User u = session.getUser();
            u.setBalance(u.getBalance() - cost);
            userRepository.save(u);

            Computer computer = session.getComputer();
            computer.setStatus("AVAILABLE");
            computerRepository.save(computer);

            return sessionRepository.save(session);
        }
        return null;
    }

    public List<ComputerSession> getAllSessions() {
        return sessionRepository.findAll();
    }

}