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
public class CheckOut {

    private String bookingID;
    private String roomID;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

  
    private long totalAmount;
    private Date checkOutDate;

    public CheckOut(String bookingID, String roomID, long totalAmount, Date checkOutDate) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.totalAmount = totalAmount;
        this.checkOutDate = checkOutDate;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getRoomID() {
        return roomID;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void ShowInfo() {
        System.out.println("bookingID: " + bookingID);
        System.out.println("roomID: " + roomID);
        System.out.println("totalAmount: " + totalAmount);
        System.out.println("checkOutDate: " + checkOutDate);

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
        final CheckOut other = (CheckOut) obj;
        if (this.totalAmount != other.totalAmount) {
            return false;
        }
        if (!Objects.equals(this.bookingID, other.bookingID)) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
      
        return true;
    }
}
