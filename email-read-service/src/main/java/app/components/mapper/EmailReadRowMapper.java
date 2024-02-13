package app.components.mapper;

import app.components.entity.EmailReadEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailReadRowMapper implements RowMapper<EmailReadEntity>{
    @Override
    public EmailReadEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EmailReadEntity(rs.getLong("user_id"),
                rs.getString("email"),
                rs.getString("token"));
    }
}
