package app.components.service;

import app.components.entity.Receipt;
import app.components.repository.ReceiptsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptService {
    private final ReceiptsRepo receiptsRepo;

    @Autowired
    public ReceiptService(ReceiptsRepo receiptsRepo) {
        this.receiptsRepo = receiptsRepo;
    }

    public List<Receipt> getAll(long idUser){
        return receiptsRepo.getReceipts(idUser);
    }

    public List<Receipt> getByDate(long idUser, LocalDate date){
        return receiptsRepo.findReceiptsByDate(idUser, date);
    }

    public void delete(long id){
        receiptsRepo.deleteById(id);
    }
}
