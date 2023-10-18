package br.com.ifsp.tickets.api.infra.config.auth;

import br.com.ifsp.tickets.api.app.guest.validate.ValidateGuestQROutput;
import br.com.ifsp.tickets.api.domain.shared.exceptions.MessageException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithQRCode(ValidateGuestQROutput output, String subject, String text){
        try {
            // Create a MIME message
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Configure the email
            helper.setTo(output.email());
            helper.setSubject(subject);
            helper.setText(text, true); // HTML content

            // Attach the QR code as a resource
            Resource qrCodeResource = new ByteArrayResource(output.qrImage());
            helper.addAttachment("qr-code.png", qrCodeResource);

            // Send the email
            javaMailSender.send(message);
        }catch (Exception er){
            throw new MessageException(er.getMessage());
        }
    }

    public void sendEmail(String to, String subject, String text){
        try {
            // Create a MIME message
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Configure the email
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // HTML content

            // Send the email
            javaMailSender.send(message);
        }catch (Exception er){
            throw new MessageException(er.getMessage());
        }
    }

    public static String valid(String name){
        return "<div>" +
                    "Olá, <b>" + name + "</b>" +
                ", esperamos que esteja muito ansioso para o nosso evento! </br> Utilize o código qr seguinte" +
                " para entrar no evento!"+
                "</div>";
    }

    public static String notValid(String name){
        return "<div>" +
                "Olá, <b>" + name + "</b>" +
                ", sentimos dizer que o seu cadastro não foi realizado com sucesso..." +
                " Contate alguém do grêmio para poder participar do evento!"+
                "</div>";
    }
}
