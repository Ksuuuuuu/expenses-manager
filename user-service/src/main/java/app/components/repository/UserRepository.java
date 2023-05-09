package app.components.repository;

import app.components.entity.User;
import app.components.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final static String GET_BY_LOGIN = "SELECT user_id, login, password, name, city_id FROM \"user\" WHERE login = :login";
    private final static String INSERT = "INSERT INTO \"user\" VALUES(:login, :password, :name, :city_id)";
    private static final String GET_BY_ID = "SELECT user_id, login, password, name, city_id FROM \"user\" WHERE user_id = :user_id";
    private final static String DELETE_BY_ID = "DELETE FROM \"user\" WHERE login = :login";
    private final static String UPDATE = "UPDATE \"user\" SET login = :login, password = :password, name = :name, city_id = :city_id  WHERE user_id = :user_id";
    @Autowired
    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<User> findUserByLogin(String login) {
        SqlParameterSource param = new MapSqlParameterSource("city_name", login);
        return namedParameterJdbcTemplate.query(GET_BY_LOGIN, param, new UserRowMapper()).stream().findFirst();
    }

    public Optional<User> findUserById(long id) {
        SqlParameterSource param = new MapSqlParameterSource("city_id", id);
        return namedParameterJdbcTemplate.query(GET_BY_ID, param, new UserRowMapper()).stream().findFirst();
    }

    public int create(User user) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("login", user.getLogin());
        param.addValue("password", user.getPassword());
        param.addValue("name", user.getName());
        param.addValue("city_id", user.getIdCity());
        return namedParameterJdbcTemplate.update(INSERT, param);
    }

    public int deleteById(long id) {
        SqlParameterSource param = new MapSqlParameterSource("city_id", id);
        return namedParameterJdbcTemplate.update(DELETE_BY_ID, param);
    }

    public int update(User user) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_id", user.getId());
        param.addValue("login", user.getLogin());
        param.addValue("password", user.getPassword());
        param.addValue("name", user.getName());
        param.addValue("city_id", user.getIdCity());
        return namedParameterJdbcTemplate.update(UPDATE, param);
    }
}
