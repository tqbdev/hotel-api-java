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
public class Room {

    private String roomID;
    private String roomName;
    private int roomType;
    private Date createDate;
    private boolean isActive;
    private String description;

    public Room(String roomID, String roomName, int roomType, Date createDate, boolean isActive, String description) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomType = roomType;
        this.createDate = createDate;
        this.isActive = isActive;
        this.description = description;
    }

    public Room() {
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomType() {
        return roomType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    public void ShowInfo()
    {
        System.out.println("roomID: "+roomID);
        System.out.println("roomName: "+roomName);
        System.out.println("roomType: "+roomType);
        System.out.println("createDate: "+createDate.toString());
        System.out.println("isActive: "+isActive);
        System.out.println("description: "+description);
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.roomID);
        hash = 79 * hash + Objects.hashCode(this.roomName);
        hash = 79 * hash + this.roomType;
        hash = 79 * hash + Objects.hashCode(this.createDate);
        hash = 79 * hash + (this.isActive ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.description);
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
        final Room other = (Room) obj;
        if (this.roomType != other.roomType) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.roomID, other.roomID)) {
            return false;
        }
        if (!Objects.equals(this.roomName, other.roomName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + roomID + ", roomName=" + roomName + ", roomType=" + roomType + ", createDate=" + createDate + ", isActive=" + isActive + ", description=" + description + '}';
    }

    
}
