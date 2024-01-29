package app.components.repository;

import app.components.entity.User;
import app.components.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String GET_BY_LOGIN = "SELECT user_id, login, encoded_password, name, city_id FROM \"user\" WHERE login = ?";
    private final static String INSERT = "INSERT INTO \"user\"(login, encoded_password, name, city_id) VALUES(?, ?, ?, ?)";
    private static final String GET_BY_ID = "SELECT user_id, login, encoded_password, name, city_id FROM \"user\" WHERE user_id = ?";
    private final static String DELETE_BY_ID = "DELETE FROM \"user\" WHERE user_id = ?";
    private final static String UPDATE = "UPDATE \"user\" SET name = ?, city_id = ?  WHERE user_id = ?";
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findUserByLogin(String login) {
        List<User> userList = jdbcTemplate.query(GET_BY_LOGIN, new Object[]{login}, new UserRowMapper());
        if (userList.isEmpty())
            return Optional.empty();
        return Optional.ofNullable(userList.get(0));
    }

    public Optional<User> findUserById(long id) {
        List<User> userList = jdbcTemplate.query(GET_BY_ID, new Object[]{id}, new UserRowMapper());
        if (userList.isEmpty())
            return Optional.empty();
        return Optional.of(userList.get(0));
    }

    @Transactional
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

        return keyHolder.getKeys() == null ? null : (long)keyHolder.getKeys().get("user_id");
    }

    @Transactional
    public int deleteById(long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Transactional
    public int update(User user) {
        return jdbcTemplate.update(UPDATE,  user.getName(), user.getIdCity(), user.getId());
    }
}
