package app.components.mapper;

import app.components.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("user_id"),
                rs.getString("login"),
                rs.getString("encoded_password"),
                rs.getString("name"),
                rs.getLong("city_id"));
    }
}
