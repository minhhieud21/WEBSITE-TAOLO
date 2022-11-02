package com.example.JavaSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService  {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Override
    public boolean sendSimpleMail(String recipient, String password,String link_website)
    {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<h1>Your new password request was successful!</h1>" +
                    "Dear Customer, your verification code is :  <h2>"+password+"</h2>"+
                    "This verification code is valid for 3 minutes, please go to the following link to confirm and create a new password : " +
                    "<a href='"+link_website+"'>Click in here</a>"+
                    "<br>Thank you for using the service, operated by Taolo.";
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(recipient);
            helper.setSubject("Request password recovery assistance");
            helper.setFrom("botdemosgu123@gmail.com");
            javaMailSender.send(mimeMessage);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
