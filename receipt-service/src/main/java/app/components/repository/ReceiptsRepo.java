package app.components.repository;

import app.components.entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReceiptsRepo {

    final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReceiptsRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Receipt> getReceipts(){
        return receipts;
    }

    public List<Receipt> findReceiptsByDate(LocalDate date){
        return receipts.stream()
                .filter(receipt -> receipt.getDateTime().toLocalDate().equals(date))
                .toList();
    }

    public void save(Receipt receipt){
        receipt.setId(++COUNTER);
        receipts.add(receipt);
    }

    public void deleteById(long id){
        receipts.removeIf(receipt -> receipt.getId() == id);
    }


}
