package app.components.repository;

import app.components.entity.User;
import app.components.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String GET_BY_LOGIN = "SELECT user_id, login, password, name, city_id FROM \"user\" WHERE login = ?";
    private final static String INSERT = "INSERT INTO \"user\" VALUES(?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT user_id, login, password, name, city_id FROM \"user\" WHERE user_id = ?";
    private final static String DELETE_BY_ID = "DELETE FROM \"user\" WHERE login = ?";
    private final static String UPDATE = "UPDATE \"user\" SET login = ?, password = ?, name = ?, city_id = ?  WHERE user_id = ?";
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(GET_BY_LOGIN, new Object[]{login}, new UserRowMapper()));
    }

    public Optional<User> findUserById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(GET_BY_ID, new Object[]{id}, new UserRowMapper()));
    }

    public Long create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.setLong(4, user.getIdCity());
            return pst;
        }, keyHolder);

        return keyHolder.getKey() == null ? null : keyHolder.getKey().longValue();
    }

    public int deleteById(long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    public int update(User user) {
        return jdbcTemplate.update(UPDATE, user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getIdCity());
    }
}
