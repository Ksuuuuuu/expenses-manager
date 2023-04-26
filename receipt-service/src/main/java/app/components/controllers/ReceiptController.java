package app.components.controllers;

import app.components.entity.Receipt;
import app.components.repository.ReceiptsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptsRepo receiptsRepo;

    @Autowired
    public ReceiptController(ReceiptsRepo receiptsRepo) {
        this.receiptsRepo = receiptsRepo;
    }

    @GetMapping()
    public String getAllReceipts(Model model){
        List<Receipt> receipts = receiptsRepo.getReceipts();
        model.addAttribute("receipts", receipts);
        return "receipts/getAll";
    }

    @GetMapping("/date={date}")
    public String getReceiptsByDate(@PathVariable("date") LocalDate date, Model model){
        List<Receipt> receipts = receiptsRepo.findReceiptsByDate(date);
        model.addAttribute("date", date);
        model.addAttribute("receiptsByDate", receipts);
        return "receipts/getByDate";
    }

    @GetMapping("/delete/id={id}")
    public String deleteReceipt(@PathVariable("id") long id){
        receiptsRepo.deleteById(id);
        return "redirect:/receipts";
    }

//    @GetMapping("/new")
//    public String addReceipt(@ModelAttribute("receipt") Receipt receipt){
//        return "receipts/new";
//    }

    @PostMapping("/new")
    public String create(@ModelAttribute("receipt") Receipt receipt){
        receiptsRepo.save(receipt);
        return "redirect:/receipts";
    }
}
