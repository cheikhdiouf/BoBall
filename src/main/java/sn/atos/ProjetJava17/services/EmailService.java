package sn.atos.ProjetJava17.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sn.atos.ProjetJava17.dto.EmailDto;

import java.io.IOException;
import java.util.Properties;

@Service
public class EmailService {

    //appel des valeur de application.properties
    @Value("${spring.mail.host}")
    private String smtpHost;

    @Value("${spring.mail.port}")
    private int smtpPort;

    @Value("${spring.mail.username}")
    private String smtpUsername;

    @Value("${spring.mail.password}")
    private String smtpPassword;

//    @Value("${spring.mail.from}")
//    private String from;



    //methode pour l'envoie de mail
    public String sendMail(EmailDto details, MultipartFile[] files ) throws MessagingException, IOException
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        try {
            mailSender.setHost(smtpHost);
            mailSender.setPort(smtpPort);
            mailSender.setUsername(smtpUsername);
            mailSender.setPassword(smtpPassword);
            mailSender.setJavaMailProperties(props);

            if (details.getTo() == null || details.getTo().isEmpty()) {
                return "Le destinataire ne peut pas être vide.";
            }
            if (details.getSubject() == null || details.getSubject().isEmpty()) {
                return "L'objet ne peut pas être vide.";
            }
            if (mailSender.getHost()==null || mailSender.getHost().isEmpty()) {
                return "Verifier le Host Smtp";
            }

            helper= new MimeMessageHelper(mimeMessage, true);
            helper.setTo((InternetAddress) details.getTo());
            helper.setText(details.getBody());
            helper.setSubject(details.getSubject());
            helper = addFilesFromMultipart( files,helper);
            mailSender.send(mimeMessage);
            return "Email envoyé avec succés";
        }

        catch (Exception e) {
            throw e;
            // return "Erreur lors de l'envoie du mail";
        }
    }

    //methode pour l'ajout de pieces jointes
    private MimeMessageHelper addFilesFromMultipart(MultipartFile[] files, MimeMessageHelper helper)
            throws MessagingException, IOException {
        if (files != null) {
            for (MultipartFile file : files) {
                InputStreamSource inputStreamSource = new ByteArrayResource(file.getBytes());
                helper.addAttachment(file.getOriginalFilename(), inputStreamSource);
            }
        }
        return helper;
    }



    //Cette méthode envoie un e-mail de base sans pièces jointes.
    public String sendSimpleMail(EmailDto details) throws MessagingException, IOException
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        try {
            mailSender.setHost(smtpHost);
            mailSender.setPort(smtpPort);
            mailSender.setUsername(smtpUsername);
            mailSender.setPassword(smtpPassword);
            mailSender.setJavaMailProperties(props);

            if (details.getTo() == null || details.getTo().isEmpty()) {
                return "Le destinataire ne peut pas être vide.";
            }

            if (details.getSubject() == null || details.getSubject().isEmpty()) {
                return "L'objet ne peut pas être vide.";
            }

            if (mailSender.getHost()==null || mailSender.getHost().isEmpty()) {
                return "Verifier le Host Smtp";
            }
            helper= new MimeMessageHelper(mimeMessage, true);
            String[] stringArray = new String[details.getTo().size()];
            helper.setTo((InternetAddress) details.getTo());
            helper.setText(details.getBody());
            helper.setSubject(details.getSubject());

            mailSender.send(mimeMessage);
            return "Email envoyé avec succés";
        }

        catch (Exception e) {
            throw e;
            // return "Erreur lors de l'envoie du mail";
        }
    }



    // Cette méthode envoie un e-mail simple en utilisant SimpleMailMessage
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(EmailDto details) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
        message.setTo(details.getTo().get(0));
        message.setSubject(details.getSubject());
        message.setText(details.getBody());

        mailSender.send(message);
    }


}