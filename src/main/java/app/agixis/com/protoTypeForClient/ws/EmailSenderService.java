package app.agixis.com.protoTypeForClient.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Annotation;

@Service
public class EmailSenderService implements Email {

    private JavaMailSender javaMailSender;
//
//    @Autowired
//    public EmailSenderService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public Class<?>[] groups() {
        return new Class[0];
    }

    @Override
    public Class<? extends Payload>[] payload() {
        return new Class[0];
    }

    /**
     * @return an additional regular expression the annotated element must match. The default
     * is any string ('.*')
     */
    @Override
    public String regexp() {
        return null;
    }

    /**
     * @return used in combination with {@link #regexp()} in order to specify a regular
     * expression option
     */
    @Override
    public Pattern.Flag[] flags() {
        return new Pattern.Flag[0];
    }

    /**
     * Returns the annotation type of this annotation.
     *
     * @return the annotation type of this annotation
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}