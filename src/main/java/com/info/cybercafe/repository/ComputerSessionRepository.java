package com.info.cybercafe.repository;

import com.info.cybercafe.entity.ComputerSession;
import com.info.cybercafe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerSessionRepository extends JpaRepository<ComputerSession, Long> {
    List<ComputerSession> findByUserAndEndTimeIsNull(User user);
}