package com.practice.easyschool.repository;

import com.practice.easyschool.model.Holiday;
import com.practice.easyschool.rowMapper.HolidayRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.util.List;

@Repository
public class HolidayRepository {

    final JdbcTemplate jdbcTemplate;

    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHolidays() {
        String sql = "SELECT * FROM HOLIDAYS";
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
