package app.components.mapper;

import app.components.entity.Receipt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptRowMapper implements RowMapper<Receipt> {
    @Override
    public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException {
        Receipt receipt = new Receipt();

        receipt.setId(rs.getLong("receipt_id"));
        receipt.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
        receipt.setAmount(rs.getBigDecimal("amount"));
        receipt.setFilePath(rs.getString("file_path"));
        receipt.setIdUser(rs.getLong("user_id"));

        return receipt;
    }
}
