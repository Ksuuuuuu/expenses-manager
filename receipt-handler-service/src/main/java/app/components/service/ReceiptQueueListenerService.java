package app.components.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@EnableRabbit
public class ReceiptQueueListenerService {

//    private ReceiptsRepo receiptsRepo;
//
//    @Autowired
//    public ReceiptQueueListenerService(ReceiptsRepo receiptsRepo){
//        this.receiptsRepo = receiptsRepo;
//    }
    @RabbitListener(queues = "receipt-queue")
    public void processMessage(String message) {
//обновить данные
    }
}
