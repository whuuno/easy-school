package com.practice.easyschool.controller;


import com.practice.easyschool.model.Holiday;
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

        List<Holiday> holidays =
                Arrays.asList(
                        new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
                        new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                        new Holiday(" Jan 26 ","Republic Day", Holiday.Type.FESTIVAL),
                        new Holiday(" Aug 15 ","Independence Day", Holiday.Type.FEDERAL),
                        new Holiday(" Sep 5 ","Teachers Day", Holiday.Type.FEDERAL),
                        new Holiday(" Oct 2 ","Gandhi Jyanti", Holiday.Type.FEDERAL),
                        new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
                        new Holiday(" Nov 14 ","Childrens Day", Holiday.Type.FEDERAL),
                        new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
                        new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL)
                );
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
