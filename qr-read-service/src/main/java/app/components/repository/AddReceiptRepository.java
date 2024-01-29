package app.components.repository;

import app.components.entity.ReceiptEntity;
import app.components.exception.RecordAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;

@Repository
public class AddReceiptRepository {
    final SimpleJdbcInsert jdbcInsert;
    private final static String GET_COUNT_BY_CHECKSUM = "SELECT EXISTS(SELECT count(*) FROM \"receipt\" WHERE checksum = ?)";

    @Autowired
    public AddReceiptRepository(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }

    public Long create(ReceiptEntity receipt) throws RecordAlreadyExistException {
        long checksum = receipt.getChecksum();
        if (!isUniqueRecord(checksum)){
            throw new RecordAlreadyExistException("Запись о добавляемом чеке уже существует");
        }

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("date_time", receipt.getDateTime());
        parameters.put("amount", receipt.getAmount());
        parameters.put("file_path", receipt.getFilePath());
        parameters.put("user_id", receipt.getIdUser());
        parameters.put("checksum", checksum);

        return jdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    private boolean isUniqueRecord(long checksum){
        return Boolean.FALSE.equals(jdbcInsert.getJdbcTemplate().queryForObject(GET_COUNT_BY_CHECKSUM, new Object[]{checksum}, Boolean.class));

    }




}
