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
public class Booking {

    private String bookingID;
    private String customerID;
    private String roomID;
    private Date bookingDate;
    private Date fromDate;
    private Date toDate;
    private int bookingStatus;

    public Booking(String bookingID, String customerID, String roomID, Date bookingDate, Date fromDate, Date toDate, int bookingStatus) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.roomID = roomID;
        this.bookingDate = bookingDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.bookingStatus = bookingStatus;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getRoomID() {
        return roomID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Booking other = (Booking) obj;
        if (this.bookingStatus != other.bookingStatus) {
            return false;
        }
        if (!Objects.equals(this.bookingID, other.bookingID)) {
            return false;
        }
        if (!Objects.equals(this.customerID, other.customerID)) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
      
     
        return true;
    }

    public void showInfo() {
        System.out.println("bookingID: "+ bookingID);
        System.out.println("customerID: "+ customerID);
        System.out.println("roomID: "+ roomID);
        System.out.println("bookingDate: "+ bookingDate);
        System.out.println("fromDate: "+ fromDate);
        System.out.println("toDate: "+ toDate);
        System.out.println("bookingStatus: "+ bookingStatus);
        
    }
    
    
}
