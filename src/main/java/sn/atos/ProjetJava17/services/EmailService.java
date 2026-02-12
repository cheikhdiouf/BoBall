package sn.atos.ProjetJava17.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sn.atos.ProjetJava17.entites.Reservation;

import java.time.format.DateTimeFormatter; // <-- ajouté

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReservationNotification(Reservation reservation) throws MessagingException {

        String fullName = reservation.getFirstName() + " " + reservation.getLastName();

        // Formater la date au format dd-MM-yyyy
        String formattedDate = "Non définie";
        if (reservation.getDepartureDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            formattedDate = reservation.getDepartureDate().toLocalDate().format(formatter);
        }

        String subject = "Nouvelle réservation – " + fullName;

        // Template HTML moderne et léger
        String htmlBody = "<html>" +
                "<body style='font-family: Arial, sans-serif; margin:0; padding:0; background-color:#f4f4f4;'>" +

                // Container principal compact
                "<div style='max-width: 380px; margin: 20px auto; background-color: #ffffff; padding: 15px; border-radius: 10px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);'>" +

                // Header
                "<div style='text-align: center; padding: 10px; background-color: #eeba1b; border-radius: 10px 10px 0 0;'>" +
                "<h2 style='color: #fff; font-size: 18px; margin: 5px 0;'>Nouvelle réservation</h2>" +
                "<p style='color: #fff; font-size: 12px; margin: 0;'>Réservation enregistrée avec succès !</p>" +
                "</div>" +

                // Info client harmonisée
                "<div style='padding: 10px; background-color: #fff8e1; border-radius: 6px; margin: 10px 0; color: #333; font-size: 12px; line-height: 1.4;'>" +
                "<p style='margin:3px;'><strong>Client :</strong> " + fullName + "</p>" +
                "<p style='margin:3px;'><strong>Programme :</strong> " + reservation.getPackageType() + "</p>" +
                "<p style='margin:3px;'><strong>Date :</strong> " + formattedDate + "</p>" + // <-- ici
                "<p style='margin:3px;'><strong>Téléphone :</strong> " + reservation.getPhone() + "</p>" +
                "<p style='margin:3px;'><strong>Email :</strong> " + reservation.getEmail() + "</p>" +
                "<p style='margin:3px;'><strong>Personnes :</strong> " + reservation.getNumberOfPeople() + "</p>" +
                "<p style='margin:3px;'><strong>Chambre :</strong> " + reservation.getRoomType() + "</p>" +
                "</div>" +

                // Image compacte
                "<div style='text-align: center; margin-bottom: 10px;'>" +
                "<img src='cid:kaabaImage' " +
                "alt='La Mecque' style='width:50%; max-width:100px; border-radius: 6px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'/>" +
                "</div>" +

                // Footer léger
                "<div style='text-align: center; color:#555; font-size: 10px; margin-top: 5px;'>" +
                "<p style='margin: 2px;'>Machallah Pèlerinage by Adja Houreye</p>" +
                "<p style='margin: 2px;'>Service Réservation</p>" +
                "</div>" +

                "</div>" +
                "</body></html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo("machala.mbs@gmail.com ");
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        // Image locale
        helper.addInline("kaabaImage", new ClassPathResource("images/image1.jpeg"));

        mailSender.send(message);
    }
}
