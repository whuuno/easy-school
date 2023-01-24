package com.practice.easyschool.repository;

import com.practice.easyschool.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHolidays() {
        String sql = "SELECT * FROM HOLIDAYS";
        //Note : If data is not fetched, take look at table name or columns cases are they upeercase or lowercasse in db
        //For MySQL it works but it is better to try this.
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
