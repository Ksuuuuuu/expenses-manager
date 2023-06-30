package app.components.controllers;

import app.components.entity.ReceiptContent;
import app.components.exception.AppError;
import app.components.exception.NotFoundParameterException;
import app.components.Status;
import app.components.entity.Receipt;
import app.components.service.ReceiptService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getAllReceipts(@PathVariable("idUser") Long idUser){
        try {
            List<ReceiptContent> receipts = receiptService.getAll(idUser).stream()
                                            .map(ReceiptContent::fromReceipt).toList();
            return new ResponseEntity<>(gson.toJson(receipts), HttpStatus.OK);
        } catch (NotFoundParameterException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No idUser parameter " + idUser),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idUser}/{date}")
    public ResponseEntity<?> getReceiptsByDate(@PathVariable("idUser") Long idUser, @PathVariable("date") LocalDate date){
        try {
            List<ReceiptContent> receipts =  receiptService.getByDate(idUser, date).stream()
                    .map(ReceiptContent::fromReceipt).toList();
            return new ResponseEntity<>(gson.toJson(receipts), HttpStatus.OK);
        } catch (NotFoundParameterException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No idUser parameter " + idUser + " or date " + date),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable("id") long id){
        try {
            if (receiptService.delete(id).equals(Status.Success)){
                return new ResponseEntity<>(new AppError(HttpStatus.OK.value(), "delete success"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No receipt with id: " + id),
                    HttpStatus.NOT_FOUND);
        } catch (NotFoundParameterException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No id parameter " + id),
                                        HttpStatus.NOT_FOUND);
        }
    }
}
