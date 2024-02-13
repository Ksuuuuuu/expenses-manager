package app.components.repository;

import app.components.entity.EmailOutboxEntity;
import app.components.mapper.EmailOutboxMapper;
import app.components.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmailOutboxRepository {
    private final SimpleJdbcInsert jdbcInsert;
    @Autowired
    public EmailOutboxRepository(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }

    @Transactional
    public Long create(EmailOutboxEntity outboxUser) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("timestamp", LocalDateTime.now());
        parameters.put("operation",outboxUser.getOperationType().getTitle());
        parameters.put("user_id", outboxUser.getIdUser());
        parameters.put("email", outboxUser.getEmail());
        parameters.put("status", "wait");

        return jdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    @Transactional
    public List<EmailOutboxEntity> getWaitingOperations(){
        String sql = "SELECT id, operation, user_id, email FROM email_outbox WHERE status = 'wait'";
        return jdbcInsert.getJdbcTemplate().query(sql, new EmailOutboxMapper());
    }

    public int  setSendStatus(List<Long> ids){
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = String.format("UPDATE email_outbox SET status = 'send' WHERE id IN (%s)", inSql);
        return jdbcInsert.getJdbcTemplate().update(sql);
    }
}
