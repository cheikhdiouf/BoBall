package sn.atos.ProjetJava17.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sn.atos.ProjetJava17.entites.DemandeDevis;
import sn.atos.ProjetJava17.entites.Reservation;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private static final String DESTINATION_EMAIL = "dioufmya65@gmail.com";
    private static final String IMAGE_PATH = "images/image1.jpeg";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /* ============================================================
                            RESERVATION
    ============================================================ */

    @Async
    public void sendReservationNotification(Reservation reservation) {

        if (reservation == null) return;

        String fullName = buildFullName(
                reservation.getFirstName(),
                reservation.getLastName()
        );

        String formattedDate = formatDate(reservation.getDepartureDate());

        StringBuilder content = new StringBuilder();
        content.append(buildField("Client", fullName));
        content.append(buildField("Programme", reservation.getPackageType()));
        content.append(buildField("Date", formattedDate));
        content.append(buildField("Téléphone", reservation.getPhone()));
        content.append(buildField("Email", reservation.getEmail()));
        content.append(buildField("Personnes",
                reservation.getNumberOfPeople() != null ?
                        String.valueOf(reservation.getNumberOfPeople()) : null));
        content.append(buildField("Chambre", reservation.getRoomType()));

        sendEmail(
                "Nouvelle réservation " + safeValue(fullName),
                "Nouvelle réservation",
                "Réservation enregistrée avec succès !",
                content.toString(),
                "Service Réservation"
        );
    }

    /* ============================================================
                            CONTACT
    ============================================================ */

    @Async
    public void sendContactNotification(Reservation reservation) {

        if (reservation == null) return;

        String fullName = buildFullName(
                reservation.getFirstName(),
                reservation.getLastName()
        );

        StringBuilder content = new StringBuilder();
        content.append(buildField("Client", fullName));
        content.append(buildField("Téléphone", reservation.getPhone()));
        content.append(buildField("Email", reservation.getEmail()));

        sendEmail(
                "Nouveau contact – " + safeValue(fullName),
                "Nouveau message de contact",
                "Un client vous a contacté.",
                content.toString(),
                "Service Commercial"
        );
    }

    /* ============================================================
                            DEMANDE DEVIS
    ============================================================ */

    @Async
    public void sendDemandeDevis(DemandeDevis devis) {

        if (devis == null) return;

        String fullName = safeValue(devis.getName());
        String formattedDate = formatDate(devis.getDate());

        StringBuilder content = new StringBuilder();
        content.append(buildField("Client", fullName));
        content.append(buildField("Email", devis.getEmail()));
        content.append(buildField("Téléphone", devis.getPhone()));
        content.append(buildField("Type de voyage", devis.getTripType()));
        content.append(buildField("Nombre de personnes",
                devis.getPeople() != null ?
                        String.valueOf(devis.getPeople()) : null));
        content.append(buildField("Date prévue", formattedDate));
        content.append(buildField("Pays", devis.getPays()));
        content.append(buildField("Type de chambre", devis.getChambre()));
        content.append(buildField("Budget", devis.getBudget()));
        content.append(buildMessageBlock(devis.getMessage()));

        sendEmail(
                "Nouvelle demande de devis " + safeValue(fullName),
                "Nouvelle demande de devis",
                "Un client souhaite recevoir une offre.",
                content.toString(),
                "Service Commercial"
        );
    }

    /* ============================================================
                            CORE MAIL
    ============================================================ */

    private void sendEmail(String subject,
                           String title,
                           String subtitle,
                           String content,
                           String footerService) {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(DESTINATION_EMAIL);
            helper.setSubject(subject);
            helper.setText(
                    buildHtmlTemplate(title, subtitle, content, footerService),
                    true
            );

            helper.addInline("kaabaImage",
                    new ClassPathResource(IMAGE_PATH));

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(
                    "Erreur lors de l'envoi de l'email", e);
        }
    }

    /* ============================================================
                            HTML TEMPLATE
    ============================================================ */

    private String buildHtmlTemplate(String title,
                                     String subtitle,
                                     String content,
                                     String footerService) {

        return "<html>" +
                "<body style='font-family:Arial,sans-serif;background:#f4f4f4;margin:0;padding:0;'>" +

                "<div style='max-width:400px;margin:20px auto;background:#fff;border-radius:12px;" +
                "box-shadow:0 3px 8px rgba(0,0,0,0.08);overflow:hidden;'>" +

                "<div style='background:#eeba1b;padding:14px;text-align:center;'>" +
                "<h2 style='margin:0;font-size:18px;color:#fff;'>" + title + "</h2>" +
                "<p style='margin:3px 0 0;font-size:12px;color:#fff;'>" + subtitle + "</p>" +
                "</div>" +

                "<div style='padding:15px;font-size:13px;color:#333;line-height:1.5;'>" +
                content +
                "</div>" +

                "<div style='text-align:center;margin-bottom:10px;'>" +
                "<img src='cid:kaabaImage' style='width:40%;max-width:80px;border-radius:6px;'/>" +
                "</div>" +

                "<div style='text-align:center;font-size:10px;color:#777;padding-bottom:10px;'>" +
                "<p style='margin:2px;'>Machalla Pèlerinage</p>" +
                "<p style='margin:2px;'>" + footerService + "</p>" +
                "</div>" +

                "</div></body></html>";
    }

    /* ============================================================
                            UTILITIES
    ============================================================ */

    private String buildField(String label, String value) {
        if (value == null || value.isBlank())
            return "";
        return "<p><strong>" + label + " :</strong> " + value + "</p>";
    }

    private String buildMessageBlock(String message) {
        if (message == null || message.isBlank())
            return "";
        return "<div style='margin-top:10px;padding:10px;background:#fff8e1;border-radius:6px;'>" +
                "<strong>Message :</strong><br/>" +
                message +
                "</div>";
    }

    private String formatDate(ZonedDateTime date) {
        if (date == null) return null;
        return date.toLocalDate().format(DATE_FORMATTER);
    }

    private String buildFullName(String firstName, String lastName) {
        return (safeValue(firstName) + " " + safeValue(lastName)).trim();
    }

    private String safeValue(String value) {
        return value == null ? "" : value.trim();
    }
}
