package com.travelneer.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class UserMailer {

    private final JavaMailSender javaMailSender;

    @Autowired
    public UserMailer(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendPasswordResetEmail(Email email, String passwordResetToken) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getValue());
        message.setSubject("Travelneer - Password Reset");
        message.setText("Your password reset token is " + passwordResetToken);

        javaMailSender.send(message);
    }

}
