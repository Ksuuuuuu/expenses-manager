package app.components.controllers;

import app.components.entity.Receipt;
import app.components.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/{idUser}")
    public String getAllReceipts(@PathVariable("idUser") Long idUser, Model model){
        if (idUser != null){
            List<Receipt> receipts = receiptService.getAll(idUser);
            model.addAttribute("receipts", receipts);
        }
        return "receipts/getAll";
    }

    @GetMapping("/{idUser}/{date}")
    public String getReceiptsByDate(@PathVariable("idUser") Long idUser, @PathVariable("date") LocalDate date, Model model){
        List<Receipt> receipts =  receiptService.getByDate(idUser, date);
        model.addAttribute("date", date);
        model.addAttribute("receiptsByDate", receipts);
        return "receipts/getByDate";
    }

    @GetMapping("/delete/{id}")
    public String deleteReceipt(@PathVariable("id") long id){
        receiptService.delete(id);
        return "redirect:/receipts";
    }
}
