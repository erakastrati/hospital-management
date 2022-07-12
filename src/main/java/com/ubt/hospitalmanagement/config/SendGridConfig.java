package com.ubt.hospitalmanagement.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${spring.sendgrid.api-key}")
    String sendGridApiKey;

    @Bean
    public SendGrid configSendgrid() {
        return new SendGrid(sendGridApiKey);
    }

}