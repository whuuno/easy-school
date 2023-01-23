package com.practice.easyschool.services;

import com.practice.easyschool.model.Holiday;
import com.practice.easyschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    HolidayRepository holidayRepository;

    public List<Holiday> findAllHolidays() {
        List<Holiday> holidays = holidayRepository.findAllHolidays();
        return holidays;
    }
}
