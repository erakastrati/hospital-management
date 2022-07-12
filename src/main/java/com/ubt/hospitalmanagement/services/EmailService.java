package com.ubt.hospitalmanagement.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.ubt.hospitalmanagement.dtos.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendGrid sendGridClient;
    @Value("${spring.sendgrid.reply-to}")
    private String replyToEmail;

    @Value("${spring.sendgrid.default-email}")
    private String defaultEmailFrom;

    public void sendEmail(EmailDTO emailDTO) {
        Mail mail = configureMail(emailDTO.getTemplateId());
        Map<String, String> templateDynamicData = emailDTO.getTemplateKeys();

        Personalization personalization = new Personalization();
        for(String key : templateDynamicData.keySet()) {
            personalization.addDynamicTemplateData(key, templateDynamicData.get(key));
        }
        personalization.addTo(new Email(emailDTO.getToEmail()));
        mail.addPersonalization(personalization);
        sendText(mail);
    }

    public void sendText(Mail mail) {
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGridClient.api(request);
        } catch (IOException ex) {
            System.out.println(ex);
            return;
        }
    }

    private Mail configureMail(String templateId) {
        Email from = new Email(defaultEmailFrom, "Hospital Management");

        Mail mail = new Mail();

        mail.setFrom(from);
        mail.setReplyTo(new Email(replyToEmail, "Hospital Management"));
        mail.setTemplateId(templateId);
        return mail;
    }
}