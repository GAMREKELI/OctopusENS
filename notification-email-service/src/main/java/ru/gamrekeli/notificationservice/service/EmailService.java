package ru.gamrekeli.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.gamrekeli.notificationservice.message.Message;
import ru.gamrekeli.notificationservice.model.User;

import java.util.logging.Logger;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void senMessage(Message message) {
        String subject = "Оповещение от " + message.getUser().getLogin();
        String body = "Ваш друг " + message.getUser().getFirstName() + " " + message.getUser().getLastName()
                + ", нуждается в вашей помощи\n" +
                message.getTime().toString();
        for (User user: message.getUserList()) {
            sendNewMail(user.getEmail(), subject, body);
        }
    }

    public void sendNewMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

//        message.setFrom("nekkimark2@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
