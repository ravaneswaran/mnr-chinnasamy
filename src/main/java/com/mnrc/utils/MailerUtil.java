package com.mnrc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailerUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailMessage(String from, String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        if(null != pathToAttachment) {
            File file = new File(pathToAttachment);
            FileSystemResource fileSystemResource
                    = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(file.getName(), fileSystemResource);
        }

        this.javaMailSender.send(message);
    }

    public void sendMailMessage(String from, String to, String subject, String text) throws MessagingException {
        this.sendMailMessage(from, to, subject, text, null);
    }

}
