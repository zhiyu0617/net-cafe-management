package com.info.cybercafe.service;

import com.info.cybercafe.dto.BookingRequest;
import com.info.cybercafe.entity.Booking;
import com.info.cybercafe.repository.BookingRepository;
import com.info.cybercafe.repository.UserRepository;
import com.info.cybercafe.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComputerRepository computerRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();

        booking.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        booking.setComputer(computerRepository.findById(request.getComputerId())
                .orElseThrow(() -> new RuntimeException("Computer not found")));

        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus("ACTIVE");

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }
}
