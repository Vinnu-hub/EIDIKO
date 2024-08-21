package com.example.AirlinesBookingApplication.service;


import com.example.AirlinesBookingApplication.entity.Booking;
import com.example.AirlinesBookingApplication.entity.FlightsEntity;
import com.example.AirlinesBookingApplication.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


    public interface AirlineService {


        void registerUser(User user);

        void updateUser(Long id, User user);

        void deleteUser(Long id);

        User getUser(Long id);

       Iterable<User> getAllUsers();
//=================================================================================




        //  Iterable<FlightsEntity> searchFlights(String source, String destination, String date);
        //void bookFlight(Long flightId, Long userId) throws Exception;
       // void updateBooking(Long id, Booking booking);

      //  void uploadDocument(MultipartFile file, Long userId, String documentType) throws IOException;
       // byte[] downloadDocument(Long id);

//void processPayment(Long bookingId, Double amount) throws Exception;
      //  byte[] downloadReceipt(Long bookingId);
      //  void sendEmailNotification(String to, String subject, String text);
    }


