package com.webapp.io_seatimecount.hybernate.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("timeToAdd")
public class TimeToAdd {
    private int id;


    private String contractStart;


    private String contractFinish;


    private String shipNAME;


    @Override
    public String toString() {
        return "TimeToAdd{" +
                "id=" + id +
                ", contractStart='" + contractStart + '\'' +
                ", contractFinish='" + contractFinish + '\'' +
                ", shipNAME='" + shipNAME + '\'' +
                '}';
    }

    public TimeToAdd(String contractStart, String contractFinish) {
        this.contractStart = contractStart;
        this.contractFinish = contractFinish;
    }

    public TimeToAdd() {
    }


    public TimeToAdd(String contractStart, String contractFinish, String shipNAME) {
        this.contractStart = contractStart;
        this.contractFinish = contractFinish;
        this.shipNAME = shipNAME;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractStart() {
        return contractStart;
    }

    public void setContractStart(String contractStart) {
        this.contractStart = contractStart;
    }

    public String getContractFinish() {
        return contractFinish;
    }

    public void setContractFinish(String contractFinish) {
        this.contractFinish = contractFinish;
    }

    public String getShipNAME() {
        return shipNAME;
    }

    public void setShipNAME(String shipNAME) {
        this.shipNAME = shipNAME;
    }


}
