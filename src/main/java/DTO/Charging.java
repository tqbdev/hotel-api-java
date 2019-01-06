/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author lap10467
 */
public class Charging {

    private String chargeID;
    private String bookingID;
    private long totalAmount;
    private Date chargeDate;
    private long tpeTransID;
    private int tpeReturnCode;
    private int chargeStatus;

    public Charging(String chargeID, String bookingID, long totalAmount, Date chargeDate, long tpeTransID, int tpeReturnCode, int chargeStatus) {
        this.chargeID = chargeID;
        this.bookingID = bookingID;
        this.totalAmount = totalAmount;
        this.chargeDate = chargeDate;
        this.tpeTransID = tpeTransID;
        this.tpeReturnCode = tpeReturnCode;
        this.chargeStatus = chargeStatus;
    }

    public String getChargeID() {
        return chargeID;
    }

    public void setChargeID(String chargeID) {
        this.chargeID = chargeID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
    }

    public void setTpeTransID(long tpeTransID) {
        this.tpeTransID = tpeTransID;
    }

    public void setTpeReturnCode(int tpeReturnCode) {
        this.tpeReturnCode = tpeReturnCode;
    }

    public void setChargeStatus(int chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getBookingID() {
        return bookingID;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public Date getChargeDate() {
        return chargeDate;
    }

    public long getTpeTransID() {
        return tpeTransID;
    }

    public int getTpeReturnCode() {
        return tpeReturnCode;
    }

    public int getChargeStatus() {
        return chargeStatus;
    }

    public void ShowInfo() {
        System.out.println("chargeID: " + chargeID);
        System.out.println("bookingID: " + bookingID);
        System.out.println("totalAmount: " + totalAmount);
        System.out.println("chargeDate: " + chargeDate.toString());
        System.out.println("tpeTransID: " + tpeTransID);
        System.out.println("tpeReturnCode: " + tpeReturnCode);
        System.out.println("chargeStatus: " + chargeStatus);

    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Charging other = (Charging) obj;
        if (this.totalAmount != other.totalAmount) {
            return false;
        }
        if (this.tpeTransID != other.tpeTransID) {
            return false;
        }
        if (this.tpeReturnCode != other.tpeReturnCode) {
            return false;
        }
        if (this.chargeStatus != other.chargeStatus) {
            return false;
        }
        if (!Objects.equals(this.chargeID, other.chargeID)) {
            return false;
        }
        if (!Objects.equals(this.bookingID, other.bookingID)) {
            return false;
        }
     
        return true;
    }

}
