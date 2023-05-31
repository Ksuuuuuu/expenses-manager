package app.components.controllers;

import app.components.exception.AppError;
import app.components.exception.NotFoundParameterException;
import app.components.Status;
import app.components.entity.Receipt;
import app.components.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getAllReceipts(@PathVariable("idUser") Long idUser){
        try {
            List<Receipt> receipts = receiptService.getAll(idUser);
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (NotFoundParameterException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No idUser parameter " + idUser),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idUser}/{date}")
    public ResponseEntity<?> getReceiptsByDate(@PathVariable("idUser") Long idUser, @PathVariable("date") LocalDate date){
        try {
            List<Receipt> receipts =  receiptService.getByDate(idUser, date);
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        } catch (NotFoundParameterException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No idUser parameter " + idUser + " or date " + date),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable("id") long id){
        try {
            if (receiptService.delete(id).equals(Status.Success)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No receipt with id: " + id),
                    HttpStatus.NOT_FOUND);
        } catch (NotFoundParameterException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "No id parameter " + id),
                                        HttpStatus.NOT_FOUND);
        }
    }
}
