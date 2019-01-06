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
public class Customer {
 private String customerID;
 private String fullName;
 private String address;
 private Date dayOfBirth;
 private String phoneNumber;
 private Date createDate;

    public String getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Customer(String customerID, String fullName, String address, Date dayOfBirth, String phoneNumber, Date createDate) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.address = address;
        this.dayOfBirth = dayOfBirth;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
 
 
    public void ShowInfo()
    {
        System.out.println("ID: "+ customerID);
        System.out.println("Name: "+ fullName);
        System.out.println("Address: "+address);
        System.out.println("Day of Birth: "+ dayOfBirth.toString());
        System.out.println("Phone number: "+phoneNumber);
        System.out.println("Create date: "+ createDate);
        System.out.println("\n");
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.customerID, other.customerID)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
       
      
        return true;
    }
 
 
 
}
