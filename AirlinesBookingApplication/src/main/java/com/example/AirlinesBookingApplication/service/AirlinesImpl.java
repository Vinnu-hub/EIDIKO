package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.entity.Documents;
import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.entity.User;
import com.example.AirlinesBookingApplication.repository.BookingFlightRepository;
import com.example.AirlinesBookingApplication.repository.DocumentsRepsoitory;
import com.example.AirlinesBookingApplication.repository.FlightEntityRepository;
import com.example.AirlinesBookingApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class AirlinesImpl implements AirlineService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private FlightEntityRepository flightEntityRepository;


    @Autowired
    private DocumentsRepsoitory documentsRepsoitory;
    @Autowired
    private BookingFlightRepository bookingFlightRepository;

    @Override
    public void registerUser(User user) { //post  > upload
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobileNumber(user.getMobileNumber());
        existingUser.setAadhaarNumber(user.getAadhaarNumber());
        existingUser.setPanNumber(user.getPanNumber());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found"));
    }


    @Override
    public Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }
}


//================================================================================================
//userRepository
//    @Override
//    public void bookFlight(Long flightId, Long userId) throws Exception {
//        FlightsEntity flight = flightEntityRepository.findById(flightId)
//                .orElseThrow(() -> new RuntimeException("Flight not found"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Assume Booking is an entity and BookingRepository is set up
//        Booking booking = new Booking();
//        booking.setFlight(flight);
//        booking.setUser(user); // Save booking
//        bookingFlightRepository.save(booking);
//
//        // Example email notification
//        //  emailService.sendEmail(user.getEmail(), "Flight Booking Confirmation", "Your flight has been booked.");
//    }
//
//
//    @Override
//    public void updateBooking(Long id, Booking booking) {
//        Booking existingBooking = bookingFlightRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//        existingBooking.setFlight(booking.getFlight());
//        existingBooking.setUser(booking.getUser());
//        existingBooking.setBookingDate(booking.getBookingDate());
//        bookingFlightRepository.save(existingBooking);
//    }
//}
////================================================================================================
