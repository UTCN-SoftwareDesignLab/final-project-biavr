package com.recruitment.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmationEmail(String to, String filename) throws MessagingException {
        MimeMessage message =  javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message, true);
        mail.setTo(to);
        mail.setFrom("bianca.avram99@gmail.com");
        mail.setSubject("APPLICATION REGISTERED");
        mail.setText("Your application was registered successfully.\nOur recruiters will contact you for further details.\nFind your application summary in the attachment.");

        FileSystemResource file
                = new FileSystemResource(new File(filename));
        mail.addAttachment("Summary.pdf", file);
        System.out.println(filename);
        javaMailSender.send(message);
    }

    public void sendReviewEmail(String to, String message){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setFrom("bianca.avram99@gmail.com");
        mail.setSubject("APPLICATION REVIEWED");
        mail.setText("Your application was reviewed by our recruiters. Here is our message:\n\n"+message);

        javaMailSender.send(mail);
    }
}
