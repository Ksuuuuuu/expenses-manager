package app.components.service;

import app.components.exception.NotFoundParameterException;
import app.components.Status;
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

    public List<Receipt> getAll(Long idUser) throws NotFoundParameterException {
        if (idUser == null){
            throw new NotFoundParameterException("No idUser");
        }
        return receiptsRepo.getReceipts(idUser);
    }

    public List<Receipt> getByDate(Long idUser, LocalDate date) throws NotFoundParameterException {
        if (idUser == null || date == null){
            throw new NotFoundParameterException("incorrect amount of data");
        }
        return receiptsRepo.findReceiptsByDate(idUser, date);
    }

    public Status delete(Long id) throws NotFoundParameterException {
        if (id == null){
            throw new NotFoundParameterException("No id");
        }
        if (receiptsRepo.deleteById(id) == 1){
            return Status.Success;
        }
        return Status.ServerError;
    }

}
