package com.webapp.io_seatimecount.countingLogic;


import com.webapp.io_seatimecount.controllers.MainController;
import com.webapp.io_seatimecount.hybernate.entity.TimeToAdd;


import java.time.LocalDate;

public class SeaTimeCalc {

    public static int calculationOfDays(TimeToAdd entry) {

        LocalDate beginningOfTheContract = parsingDate(entry.getContractStart());
        LocalDate endOfTheContract = parsingDate(entry.getContractFinish());
        return calculateSeaTime(beginningOfTheContract, endOfTheContract);

    }

    private static LocalDate parsingDate(String date) {
        int year = Integer.parseInt(date.substring(0, date.indexOf("-")));
        int month = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
        int day = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1));
        return LocalDate.of(year, month, day);

    }


    private static int calculateSeaTime(LocalDate beginningOfTheContract, LocalDate endOfTheContract) {
        if (beginningOfTheContract.isAfter(endOfTheContract)) {
            ControllerLogic.setWrongDates(true);
        }

        LocalDate ldStart = beginningOfTheContract;
        int days = 0;

        while (endOfTheContract.isAfter(ldStart) || endOfTheContract.isEqual(ldStart)) {
            days++;
            ldStart = ldStart.plusDays(1);
        }
        return days;


    }


}
