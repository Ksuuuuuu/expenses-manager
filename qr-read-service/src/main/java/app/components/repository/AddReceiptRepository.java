package app.components.repository;

import app.components.entity.ReceiptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AddReceiptRepository {
    final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public AddReceiptRepository(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }

    public Long create(ReceiptEntity receipt){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("date_time", receipt.getDateTime());
        parameters.put("amount", receipt.getAmount());
        parameters.put("filePath", receipt.getFilePath());
        parameters.put("user_id", receipt.getIdUser());

        return jdbcInsert.executeAndReturnKey(parameters).longValue();
    }

}
