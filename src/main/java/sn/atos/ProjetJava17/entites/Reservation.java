
package sn.atos.ProjetJava17.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Integer id;

    // Informations personnelles
    @Column(name = "res_first_name", nullable = false)
    private String firstName;

    @Column(name = "res_last_name", nullable = false)
    private String lastName;

    @Column(name = "res_email")
    private String email;

    @Column(name = "res_phone", nullable = false)
    private String phone;

    @Column(name = "res_date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "res_passport_number")
    private String passportNumber;

    @Column(name = "res_passport_expiry")
    private LocalDate passportExpiry;

    // Choix du voyage
    @Column(name = "res_package_type")
    private String packageType;

    @Column(name = "res_departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private ZonedDateTime departureDate;

    @Column(name = "res_number_of_people")
    private Integer numberOfPeople;

    @Column(name = "res_room_type")
    private String roomType;

    // Mode de paiement
    @Column(name = "res_payment_method")
    private String paymentMethod;

    @Column(name = "res_mobile_money_provider")
    private String mobileMoneyProvider;

    @Column(name = "res_mobile_money_number")
    private String mobileMoneyNumber;

    @Column(name = "res_bank_name")
    private String bankName;

    // Informations supplémentaires
    @Column(name = "res_special_requests", length = 1000)
    private String specialRequests;
}
