package project_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail (String toEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lesliebobo93@gmail.com");
        message.setTo(toEmail);
        message.setText("This email serves to confirm that you have completed a survey");
        message.setSubject("Survey Completion");
        mailSender.send(message);

        System.out.println("Mail is sent successfully >>>>");

    }
}
