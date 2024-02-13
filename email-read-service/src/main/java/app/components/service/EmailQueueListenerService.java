package app.components.service;

import app.components.entity.EmailWriteEntity;
import app.components.repository.EmailRepository;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class EmailQueueListenerService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailQueueListenerService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @RabbitListener(queues = "email-queue")
    public void processMessage(String message) {
        Gson gson = new Gson();
        EmailWriteEntity emailWriteEntity = gson.fromJson(message , EmailWriteEntity.class);
        switch (emailWriteEntity.getOperation()){
            case "create" -> emailRepository.create(emailWriteEntity);
            case "update" -> emailRepository.update(emailWriteEntity);
            case "delete" -> emailRepository.delete(emailWriteEntity.getUserId());

        }

    }
}
