package app.components.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReceiptOutboxRepository {

    private final SimpleJdbcInsert jdbcInsert;
    @Autowired
    public ReceiptOutboxRepository(@Qualifier("jdbcInsertTemplateToReceiptOutbox") SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }

    @Transactional
    public Long create(Long receiptId) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("timestamp", LocalDateTime.now());
        parameters.put("receipt_id",receiptId);
        parameters.put("status", "wait");

        return jdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    public List<Long> getWaitingReceiptsIds(){
        String sql = "SELECT receipt_id FROM receipt_outbox WHERE status = 'wait'";
        return jdbcInsert.getJdbcTemplate().query(sql,(rs, rowNum) -> rs.getLong("receipt_id"));
    }

    public int  setSendStatus(List<Long> ids){
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = String.format("UPDATE receipt_outbox SET status = 'send' WHERE id IN (%s)", inSql);
        return jdbcInsert.getJdbcTemplate().update(sql);
    }
}
