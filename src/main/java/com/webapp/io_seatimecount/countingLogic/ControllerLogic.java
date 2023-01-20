package com.webapp.io_seatimecount.countingLogic;

import com.webapp.io_seatimecount.hybernate.entity.TimeToAdd;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;





@Component("controllerLogic")
public class ControllerLogic {

    private long totalDays = 0;
    private List<String> listOfTheShipsAndDates = new ArrayList<>();

    public List<String> getListOfTheShipsAndDates() {
        List<String> copy = new ArrayList<>(listOfTheShipsAndDates);
        return copy;
    }

    public void clearTheList() {
        listOfTheShipsAndDates.clear();
    }

    private boolean wrongDates = false;

    public void setWrongDates(boolean wrongDates) {
        this.wrongDates = wrongDates;
    }


    public void checkValidityOfTheDates(String begin_contract_date, String end_contract_date, String vessel_name, Model model) {

        if (begin_contract_date == null || begin_contract_date.equals("") || end_contract_date == null ||
                end_contract_date.equals("")) {
            model.addAttribute("fullList", "Not enough data");
            return;
        }
        if (vessel_name == null || vessel_name.strip().length() < 2) {
            model.addAttribute("fullList", "Please enter valid vessel name");
            return;
        }


        wrongDates = false;

        TimeToAdd tta = new TimeToAdd(begin_contract_date, end_contract_date, vessel_name);

        int days = SeaTimeCalc.calculationOfDays(tta);
        // <- in the method is also include check for valid input
        //through the setter it is set up in the wrongDates below

        if (wrongDates) {
            model.addAttribute("fullList", "Wrong dates");
            return;
        }

        totalDays += days; // < - calculation of the days

        //below logic for screen total days view
        String toUseDay = "";
        if (totalDays >= 30) {
            long totalMonth = totalDays / 30;
            long remainsDays = totalDays % 30;
            String toUseMonth = "";
            if (String.valueOf(totalMonth).endsWith("1") && !String.valueOf(totalMonth).equals("11")) {
                toUseMonth = " month";
            } else {
                toUseMonth = " months";
            }
            if (remainsDays != 0) {
                if (String.valueOf(remainsDays).endsWith("1") && !String.valueOf(remainsDays).equals("11")) {
                    toUseDay = " day";
                } else {
                    toUseDay = " days";
                }
                model.addAttribute("counter", totalMonth + toUseMonth + " and " + remainsDays + toUseDay);

            } else {
                model.addAttribute("counter", totalMonth + toUseMonth);
            }
            //adding to a model to get the number through th:text in view
        } else {
            if (String.valueOf(totalDays).endsWith("1") && !String.valueOf(totalDays).equals("11")) {
                toUseDay = " day";
            } else {
                toUseDay = " days";
            }
            model.addAttribute("counter", totalDays + toUseDay);
            //adding to a model to get the number through th:text in view
        }
        model.addAttribute("shipName", tta.getShipNAME());
        listOfTheShipsAndDates.add((String) model.getAttribute("counter") + " onboard " + "\"" + model.getAttribute("shipName") + "\"");
        model.addAttribute("fullList", listOfTheShipsAndDates);
        onlyTime.addDays(totalDays);
        if (String.valueOf(onlyTime.getAllDays()).endsWith("1") && onlyTime.getAllDays() != 11) {
            model.addAttribute("totalTime", onlyTime.getAllDays() + " day total");
        } else {
            model.addAttribute("totalTime", onlyTime.getAllDays() + " days total");
        }

        totalDays = 0;
    }
}
