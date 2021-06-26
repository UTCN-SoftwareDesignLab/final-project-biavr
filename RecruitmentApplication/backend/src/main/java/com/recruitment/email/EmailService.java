package com.recruitment.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setFrom("bianca.avram99@gmail.com");
        mail.setSubject("APPLICATION REGISTERED");
        mail.setText("Your application was registered successfully. Our recruiters will contact you for further details");

        javaMailSender.send(mail);
    }
}
