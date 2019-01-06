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
public class Menu {

    private String priceID;
    private String roomID;
    private long amount;
    private boolean isActive; 
    private Date createDate;
    private String note;

    public Menu(String priceID, String roomID, long amount, boolean isActive, Date createDate, String note) {
        this.priceID = priceID;
        this.roomID = roomID;
        this.amount = amount;
        this.isActive = isActive;
        this.createDate = createDate;
        this.note = note;
    }

    public String getPriceID() {
        return priceID;
    }

    public String getRoomID() {
        return roomID;
    }

    public long getAmount() {
        return amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getNote() {
        return note;
    }

    public void setPriceID(String priceID) {
        this.priceID = priceID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setIsAcive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void ShowInfo() {
        System.out.println("priceID: " + priceID);
        System.out.println("roomID: " + roomID);
        System.out.println("amount: " + amount);
        System.out.println("isActive: " + isActive);
        System.out.println("createDate: " + createDate);
        System.out.println("note: " + note);
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Menu other = (Menu) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.priceID, other.priceID)) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
     
        return true;
    }

}
