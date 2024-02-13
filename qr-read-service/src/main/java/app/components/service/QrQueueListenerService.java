package app.components.service;

import app.components.entity.ReceiptLoad;
import app.components.exception.RecordAlreadyExistException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
@EnableRabbit
public class QrQueueListenerService {
    Logger logger = LoggerFactory.getLogger(QrQueueListenerService.class);
    private final AddReceiptService addReceiptService;

    @Autowired
    public QrQueueListenerService(AddReceiptService addReceiptService) {
        this.addReceiptService = addReceiptService;
    }

    @RabbitListener(queues = "qr-queue")
    public void processMessage(String message) throws FileNotFoundException, RecordAlreadyExistException {
        logger.info("Получили сообщение из очереди " + message);
        Gson gson = new Gson();
        ReceiptLoad receiptLoad = gson.fromJson(message , ReceiptLoad.class);
        logger.info("receipt " + receiptLoad);
        addReceiptService.save(receiptLoad);
    }
}
