package sn.atos.ProjetJava17.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationDto(
        Integer id,
        // Informations personnelles
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate dateOfBirth,
        String passportNumber,
        LocalDate passportExpiry,

        // Choix du voyage
        String packageType,
        ZonedDateTime departureDate,
        Integer numberOfPeople,
        String roomType,

        // Mode de paiement
        String paymentMethod,
        String mobileMoneyProvider,
        String mobileMoneyNumber,
        String bankName,

        // Informations supplémentaires
        String specialRequests

) {}
