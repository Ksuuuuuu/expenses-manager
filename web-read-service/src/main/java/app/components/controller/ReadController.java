package app.components.controller;

import app.components.AppResponse;
import app.components.entity.ReceiptLoad;
import app.components.service.SaveFileService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/receipts/add")
public class ReadController {
    Logger logger = LoggerFactory.getLogger(ReadController.class);
    private final static String QUEUE_NAME = "message-queue";
    private final SaveFileService saveFileService;
    private final AmqpTemplate template;

    @Autowired
    public ReadController(SaveFileService saveFileService, AmqpTemplate template) {
        this.saveFileService = saveFileService;
        this.template = template;
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> readReceipt(@RequestBody String filePath, @PathVariable Long idUser) {
        System.out.println("Загрузили файл " + filePath);
        //String path = saveFileService.saveFileToDir(new File(fileName));
        ReceiptLoad receiptLoad = new ReceiptLoad(filePath, idUser);
        //write to queue with receipt load
        System.out.println("Формируем и отправляем сообщение");
        template.convertAndSend(QUEUE_NAME, new Gson().toJson(receiptLoad));
        return ResponseEntity.ok("success");
    }
}
