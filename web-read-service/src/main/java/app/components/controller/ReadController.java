package app.components.controller;

import app.components.entity.ReceiptLoad;
import app.components.service.SaveFileService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts/add")
public class ReadController {
    Logger logger = LoggerFactory.getLogger(ReadController.class);
    private final static String QR_ROUTING_KEY = "qr";
    private final SaveFileService saveFileService;
    private final AmqpTemplate template;

    @Autowired
    public ReadController(SaveFileService saveFileService, AmqpTemplate template) {
        this.saveFileService = saveFileService;
        this.template = template;
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> readReceipt(@RequestBody String filePath, @PathVariable Long idUser) {
        logger.info("Получили путь к файлу " + filePath);
        //String path = saveFileService.saveFileToDir(new File(fileName));
        ReceiptLoad receiptLoad = new ReceiptLoad(filePath, idUser);
        //write to queue with receipt load
        logger.info("Формируем и отправляем сообщение " + receiptLoad);
        template.convertAndSend(QR_ROUTING_KEY, new Gson().toJson(receiptLoad));
        return ResponseEntity.ok("success");
    }
}
