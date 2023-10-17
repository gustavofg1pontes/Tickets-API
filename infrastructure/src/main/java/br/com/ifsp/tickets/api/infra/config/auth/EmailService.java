package br.com.ifsp.tickets.api.infra.config.auth;

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

    public void sendEmailWithQRCode(String to, String subject, String text, byte[] qrCodeImage){
        try {
            // Create a MIME message
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Configure the email
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // HTML content

            // Attach the QR code as a resource
            Resource qrCodeResource = new ByteArrayResource(qrCodeImage);
            helper.addAttachment("qr-code.png", qrCodeResource);

            // Send the email
            javaMailSender.send(message);
        }catch (Exception er){
            throw new MessageException(er.getMessage());
        }
    }
}
