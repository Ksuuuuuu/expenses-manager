package app.components.repository;

import app.components.entity.Receipt;
import app.components.mapper.ReceiptRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReceiptsRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final static String GET_ALL = "SELECT receipt_id, date_time, amount, file_path, user_id FROM receipt WHERE user_id = :user_id";
    private final static String DELETE = "DELETE FROM receipt WHERE receipt_id = :receipt_id";

    @Autowired
    public ReceiptsRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Receipt> getReceipts(long idUser){
        SqlParameterSource param = new MapSqlParameterSource("user_id", idUser);
        return namedParameterJdbcTemplate.query(GET_ALL, param, new ReceiptRowMapper());
    }

    public List<Receipt> findReceiptsByDate(long idUser, LocalDate date){
        return getReceipts(idUser).stream()
                .filter(receipt -> receipt.getDateTime().toLocalDate().equals(date))
                .toList();
    }

    public int deleteById(long id){
        SqlParameterSource param = new MapSqlParameterSource("receipt_id", id);
        return namedParameterJdbcTemplate.update(DELETE, param);
    }


}
