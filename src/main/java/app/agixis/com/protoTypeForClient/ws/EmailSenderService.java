package app.agixis.com.protoTypeForClient.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class EmailSenderService   {
    private JavaMailSenderImpl mailSender;

    @Autowired
    public EmailSenderService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setJavaMailProperties(getMailProperties());
        mailSender.setUsername("diaguilybounba@@gmail.com");
        mailSender.setPassword("BallaSylla%2016");
        mailSender.send(email);
    }
    public Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.enable","true");
        properties.setProperty("mail.test-connection","true");
        return properties;
    }
}