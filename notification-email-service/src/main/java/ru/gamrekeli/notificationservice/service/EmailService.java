package ru.gamrekeli.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.gamrekeli.notificationservice.message.Message;
import ru.gamrekeli.notificationservice.model.User;

@Service
public class EmailService implements SendMessage {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMessage(Message message) {
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

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
