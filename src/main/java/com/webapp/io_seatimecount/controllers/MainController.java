package com.webapp.io_seatimecount.controllers;


import com.webapp.io_seatimecount.components.ApplicationContextHolder;
import com.webapp.io_seatimecount.countingLogic.ControllerLogic;
import com.webapp.io_seatimecount.countingLogic.onlyTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class MainController {


    @GetMapping("/")
    public String home(Model model) {
        ApplicationContextHolder.getApplicationContext()
                .getBean("controllerLogic", ControllerLogic.class).clearTheList();
        onlyTime.clearTheDays();
        //made for cleaning the list && total days after each refresh of the page

        if (ApplicationContextHolder.getApplicationContext()
                .getBean("controllerLogic", ControllerLogic.class).getListOfTheShipsAndDates().isEmpty()) {
            model.addAttribute("fullList", "\u2063"); // <- empty symbol to keep space in HTML for counter
        }

        model.addAttribute("fullList");


        return "home";
    }

    @PostMapping("/")
    public String addSeaTime(@RequestParam(value = "begin_contract_date", required = false) String begin_contract_date,
                             @RequestParam(value = "end_contract_date", required = false) String end_contract_date,
                             @RequestParam(value = "vessel_name", required = false) String vessel_name,
                             Model model) {

        ApplicationContextHolder.getApplicationContext()
                .getBean("controllerLogic", ControllerLogic.class)
                .checkValidityOfTheDates(begin_contract_date, end_contract_date, vessel_name, model);


        return "home";
    }


}
