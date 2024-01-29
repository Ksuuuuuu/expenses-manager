package app.components.repository;

import app.components.entity.City;
import app.components.mapper.CityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CityRepository {

    private final JdbcTemplate jdbcTemplate;

    private final static String GET_BY_NAME = "SELECT city_id, city_name FROM city WHERE city_name = ?";
    private final static String GET_ALL = "SELECT city_id, city_name FROM city";
    private final static String INSERT = "INSERT INTO city(city_name) VALUES(?)";
    private static final String GET_BY_ID = "SELECT city_id, city_name FROM city WHERE city_id = ?";

    @Autowired
    public CityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<City> getCities(){
        return jdbcTemplate.query(GET_ALL, new CityRowMapper());
    }

    public Optional<City> findCityByName(String name){
        List<City> listCity = jdbcTemplate.query(GET_BY_NAME, new Object[]{name}, new CityRowMapper());
        if (listCity.isEmpty())
            return Optional.empty();
        return Optional.of(listCity.get(0));
    }

    public Optional<City> findCityById(long id){
        List<City> listCity = jdbcTemplate.query(GET_BY_ID, new Object[]{id}, new CityRowMapper());
        if (listCity.isEmpty())
            return Optional.empty();
        return Optional.of(listCity.get(0));
    }

    @Transactional
    public Long create(City city){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, city.getTitle());
            return pst;
        }, keyHolder);

        return keyHolder.getKeys() == null ? null : (long) Objects.requireNonNull(keyHolder.getKeys()).get("city_id");
    }
}
