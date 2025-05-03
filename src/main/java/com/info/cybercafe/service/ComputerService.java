package com.info.cybercafe.service;

import com.info.cybercafe.entity.Computer;
import com.info.cybercafe.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    // Fetch all available computers
    public List<Computer> getAvailableComputers() {
        return computerRepository.findByStatus("AVAILABLE");
    }

    // Fetch computer by ID
    public Computer getComputerById(Long id) {
        return computerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Computer not found"));
    }

    // Update computer status
    public Computer updateComputerStatus(Long computerId, String status) {
        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new RuntimeException("Computer not found"));

        computer.setStatus(status);
        return computerRepository.save(computer);
    }
}