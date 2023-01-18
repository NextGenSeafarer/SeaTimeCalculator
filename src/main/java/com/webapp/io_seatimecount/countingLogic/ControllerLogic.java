package com.webapp.io_seatimecount.countingLogic;

import com.webapp.io_seatimecount.hybernate.entity.TimeToAdd;

import org.springframework.ui.Model;

public class ControllerLogic {

    private static long totalDays = 0;


    public static void setTotalDays(long totalDays) {
        ControllerLogic.totalDays = totalDays;
    }

    private static boolean wrongDates = false;

    public static void setWrongDates(boolean wrongDates) {
        ControllerLogic.wrongDates = wrongDates;
    }

    public static void checkValidityOfTheDates(String begin_contract_date, String end_contract_date, String vessel_name, Model model) {

        if (begin_contract_date == null || begin_contract_date.equals("") || end_contract_date == null ||
                end_contract_date.equals("")) {
            model.addAttribute("counter", "Not enough data");
            return;
        }
        if(vessel_name == null || vessel_name.strip().length() <=2 ){
            model.addAttribute("counter", "Please enter valid vessel name");
            return;
        }


        wrongDates = false;

        TimeToAdd tta = new TimeToAdd(begin_contract_date, end_contract_date);

        int days = SeaTimeCalc.calculationOfDays(tta); // <- in the method is also include check for valid input
        //through the setter it is set up in the wrongDates below

        if (wrongDates) {
            model.addAttribute("counter", "Wrong dates");
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
                model.addAttribute("counter", totalMonth + toUseMonth + " exactly");
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
    }
}
