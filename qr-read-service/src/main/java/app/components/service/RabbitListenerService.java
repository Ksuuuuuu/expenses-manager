//package app.components.service;
//
//import app.components.entity.ReceiptLoad;
//import com.google.gson.Gson;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.io.FileNotFoundException;
//
//@Service
//@EnableRabbit
//public class RabbitListenerService {
//    private final AddReceiptService addReceiptService;
//
//    @Autowired
//    public RabbitListenerService(AddReceiptService addReceiptService) {
//        this.addReceiptService = addReceiptService;
//    }
//
//    @RabbitListener(queues = "message-queue")
//    public void processMessage(String message) throws FileNotFoundException {
//        System.out.println("Получили сообщение ");
//        Gson gson = new Gson();
//        ReceiptLoad receiptLoad = gson.fromJson(message , ReceiptLoad.class);
//        addReceiptService.save(receiptLoad);
//    }
//}
