package app.components.controller;

import app.components.AppError;
import app.components.entity.ReceiptLoad;
import app.components.service.SaveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/receipts/add")
public class ReadController {

    private final SaveFileService saveFileService;

    @Autowired
    public ReadController(SaveFileService saveFileService) {
        this.saveFileService = saveFileService;
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> readReceipt(@RequestBody File file, @PathVariable Long idUser){
        try {
            //save file to s3
            //path + idUser -> receipt load
            String path = saveFileService.saveFileToDir(file);
            ReceiptLoad receiptLoad = new ReceiptLoad(path, idUser);
            //write to queue with receipt load
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "File doesn't found"), HttpStatus.NOT_FOUND);
        }

    }
}
