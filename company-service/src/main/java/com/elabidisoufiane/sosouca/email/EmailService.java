package com.elabidisoufiane.sosouca.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendAccountCreationEmail(
            String destinationEmail,
            String rhName,
            String initialPassword,
            String companyName,
            String companyAddress
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("ilyaselmabrouki7@gmail.com");
        messageHelper.setSubject("Your HR Account Has Been Created");

        // Use the updated template name for account creation
        final String templateName = "account_creation_template"; // The Thymeleaf template file name

        // Prepare variables for the email content
        Map<String, Object> variables = new HashMap<>();
        variables.put("rhName", rhName);
        variables.put("email", destinationEmail);
        variables.put("initialPassword", initialPassword);
        variables.put("companyName", companyName);
        variables.put("companyAddress", companyAddress);
        Context context = new Context();
        context.setVariables(variables);

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("INFO - Account creation email successfully sent");
        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send account creation email", e);
        }
    }

}
