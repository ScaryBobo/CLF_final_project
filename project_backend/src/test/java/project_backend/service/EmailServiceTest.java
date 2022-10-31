package project_backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EmailServiceTest {
    @Autowired
    private EmailService emailSvc;


    @Test
    void testEmail (){
        emailSvc.sendEmail("lesbobo93@gmail.com");
    }
}