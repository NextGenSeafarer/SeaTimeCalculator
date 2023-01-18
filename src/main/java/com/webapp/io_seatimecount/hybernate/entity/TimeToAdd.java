package com.webapp.io_seatimecount.hybernate.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "seatime")
public class TimeToAdd {
    @Id // <- show that it is a primary key
    @Column(name = "id") // <- name of the column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <- type of generation value strategy
    private int id;

    @Column(name = "contract_start")
    private String contractStart;

    @Column(name = "contract_finish")
    private String contractFinish;

    @Column(name = "shipname")
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
