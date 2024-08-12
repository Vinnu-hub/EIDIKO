package com.example.secondspringAirlinesproject.mapper;

import com.example.secondspringAirlinesproject.Dto.AirlinesDto;
import com.example.secondspringAirlinesproject.entity.Airlines;

public class AirlinesMapper {

    // Convert Airlines entity to AirlinesDto
    public static AirlinesDto mapToAirlinesDto(Airlines airline) {
        if (airline == null) {
            return null;
        }
        return new AirlinesDto(
                airline.getAirlineNumber(),
                airline.getPassengerName(),
                airline.getSourceCountry(),
                airline.getBookingPassNumber(),
                airline.getAadhaarNumber(),
                airline.getMobileNumber(), // Map as Long
                airline.getDestinationCountry(),
                airline.getPassportNumber()
        );
    }

    // Convert AirlinesDto to Airlines entity
    public static Airlines mapToAirlines(AirlinesDto airlineDto) {
        if (airlineDto == null) {
            return null;
        }
        return new Airlines(
                airlineDto.getAirlineNumber(),
                airlineDto.getPassengerName(),
                airlineDto.getSourceCountry(),
                airlineDto.getBookingPassNumber(),
                airlineDto.getAadhaarNumber(),
                airlineDto.getMobileNumber(), // Map as Long
                airlineDto.getDestinationCountry(),
                airlineDto.getPassportNumber()
        );
    }
}
