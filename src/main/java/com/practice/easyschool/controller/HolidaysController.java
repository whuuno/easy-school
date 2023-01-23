package com.practice.easyschool.controller;


import com.practice.easyschool.model.Holiday;
import com.practice.easyschool.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @Autowired
    HolidayService holidayService;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display,
                                  Model model) {
        if (null != display && ("all").equals(display)) {
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }
        else if (null != display && ("festival").equals(display)) {
            model.addAttribute("festival",true);
        }
        else if (null != display && ("federal").equals(display)) {
            model.addAttribute("federal",true);
        }

        List<Holiday> holidays = holidayService.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    holidays.stream()
                            .filter(holiday -> (holiday.getType().equals(type)))
                            .collect(Collectors.toList()));
        }
        return "holidays.html";
    }
}
