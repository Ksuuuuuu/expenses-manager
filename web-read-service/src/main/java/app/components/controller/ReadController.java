package app.components.controller;

import app.components.AppError;
import app.components.entity.ReceiptLoad;
import app.components.service.SaveFileService;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/receipts/add")
public class ReadController {

    private final static String QUEUE_NAME = "message-queue";
    private final SaveFileService saveFileService;
    private final AmqpTemplate template;

    @Autowired
    public ReadController(SaveFileService saveFileService, AmqpTemplate template) {
        this.saveFileService = saveFileService;
        this.template = template;
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> readReceipt(@RequestBody File file, @PathVariable Long idUser) {
        try {
            //save file to s3
            //path + idUser -> receipt load
            String path = saveFileService.saveFileToDir(file);
            ReceiptLoad receiptLoad = new ReceiptLoad(path, idUser);
            //write to queue with receipt load
            template.convertAndSend(QUEUE_NAME, new Gson().toJson(receiptLoad));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "File doesn't found"), HttpStatus.NOT_FOUND);
        }

    }
}
