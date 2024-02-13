package app.components.repository;

import app.components.entity.EmailReadEntity;
import app.components.entity.EmailWriteEntity;
import app.components.mapper.EmailReadRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public class EmailRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String GET_EMAIL_BY_ID = "SELECT email FROM emails WHERE user_id = ?";
    private final static String GET_BY_ID = "SELECT user_id, email, token FROM emails WHERE user_id = ?";
    private final static String INSERT = "INSERT INTO emails(user_id, email) VALUES(?, ?)";
    private final static String UPDATE = "UPDATE emails SET email = ? WHERE user_id = ?";
    private final static String DELETE = "DELETE FROM emails WHERE user_id = ?";

    @Autowired
    public EmailRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<EmailReadEntity> findEmailEntityById(long idUser) {
        List<EmailReadEntity> emailList = jdbcTemplate.query(GET_BY_ID, new Object[]{idUser}, new EmailReadRowMapper());
        if (emailList.isEmpty())
            return Optional.empty();
        return Optional.ofNullable(emailList.get(0));
    }

    public String findEmailById(long idUser) {
        return jdbcTemplate.queryForObject(GET_EMAIL_BY_ID,  new Object[]{idUser}, String.class);
    }

    @Transactional
    public int create(EmailWriteEntity emailEntity){
        return jdbcTemplate.update(INSERT, emailEntity.getUserId(), emailEntity.getEmail());
    }

    @Transactional
    public int update(EmailWriteEntity emailEntity){
        return jdbcTemplate.update(UPDATE, emailEntity.getUserId(), emailEntity.getEmail(), null);
    }

    @Transactional
    public int delete(long id){
        return jdbcTemplate.update(DELETE, id);
    }
}
