package com.hartwig.emailsender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@AllArgsConstructor
@Slf4j
@Component
public class QueueConsumer {

    private final JavaMailSender mailSender;

    @RabbitListener(queues = {"email-ms"})
    public void receive(@Payload @Valid EmailPayload payload) throws Exception {
        log.info("Received payload -> {}", payload);
        if(payload == null) {
            throw new Exception("Payload must not be null");
        }
        var mail = new SimpleMailMessage();
        mail.setTo(payload.to());
        mail.setSubject(payload.subject());
        mail.setText(payload.body());
        mailSender.send(mail);
    }

}
