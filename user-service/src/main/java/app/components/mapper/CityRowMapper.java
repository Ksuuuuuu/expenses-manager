package app.components.mapper;

import app.components.entity.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();

        city.setId(rs.getLong("city_id"));
        city.setTitle(rs.getString("city_name"));

        return city;
    }
}
