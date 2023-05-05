package app.components.repository;

import app.components.entity.City;
import app.components.mapper.CityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String GET_BY_NAME = "SELECT city_id, city_name FROM city WHERE city_name = :city_name";
    private final static String GET_ALL = "SELECT city_id, city_name FROM city";
    private final static String INSERT = "INSERT INTO city VALUES(:city_id, :city_name)";
    private static final String GET_BY_ID = "SELECT city_id, city_name FROM city WHERE city_id = :city_id";

    @Autowired
    public CityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<City> getCities(){
        return namedParameterJdbcTemplate.query(GET_ALL, new CityRowMapper());
    }

    public Optional<City> findCityByName(String name){
        SqlParameterSource param = new MapSqlParameterSource("city_name", name);
        return namedParameterJdbcTemplate.query(GET_BY_NAME, new CityRowMapper()).stream().findFirst();
    }

    public Optional<City> findCityById(long id){
        SqlParameterSource param = new MapSqlParameterSource("city_id", id);
        return namedParameterJdbcTemplate.query(GET_BY_ID, new CityRowMapper()).stream().findFirst();
    }

    public void create(City city){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("city_id", city.getId());
        param.addValue("city_name", city.getTitle());
        namedParameterJdbcTemplate.update(INSERT, param);
    }
}
