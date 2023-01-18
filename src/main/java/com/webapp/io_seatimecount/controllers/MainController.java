package com.webapp.io_seatimecount.controllers;


import com.webapp.io_seatimecount.countingLogic.ControllerLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class MainController {


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("counter", "\u2063"); // <- empty symbol to keep space in HTML for counter
        ControllerLogic.setTotalDays(0);
        return "home";
    }

    @PostMapping("/")
    public String addSeaTime(@RequestParam(value = "begin_contract_date", required = false) String begin_contract_date,
                             @RequestParam(value = "end_contract_date", required = false) String end_contract_date,
                             @RequestParam(value = "vessel_name", required = false) String vessel_name,
                             Model model) {

        ControllerLogic.checkValidityOfTheDates(begin_contract_date, end_contract_date, vessel_name, model);


        //DateBaseWorkWith.pushToDB(tta); // <- push to DB


        return "home";
    }


}
