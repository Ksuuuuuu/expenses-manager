package app.components.service;

import app.components.entity.ReceiptLoad;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@EnableRabbit
@Component
public class RabbitListenerService {
    private final AddReceiptService addReceiptService;

    @Autowired
    public RabbitListenerService(AddReceiptService addReceiptService) {
        this.addReceiptService = addReceiptService;
    }

    @RabbitListener(queues = "message-queue")
    public void processMessage(String message) throws FileNotFoundException {
        Gson gson = new Gson();
        ReceiptLoad receiptLoad = gson.fromJson(message , ReceiptLoad.class);
        addReceiptService.save(receiptLoad);
    }
}
