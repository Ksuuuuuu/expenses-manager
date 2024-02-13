package app.components.mapper;

import app.components.entity.EmailOutboxEntity;
import app.components.entity.OperationType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailOutboxMapper implements RowMapper<EmailOutboxEntity> {
    @Override
    public EmailOutboxEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EmailOutboxEntity(rs.getLong("id"), rs.getLong("user_id"),
                rs.getString("email"),
                OperationType.valueOf(rs.getString("operation")));
    }
}