/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.CheckOut;
import DataAccess.CheckOutDAO;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class CheckOutBUS {

    private CheckOutDAO checkOutDAO;

    public CheckOutBUS() {
        checkOutDAO = new CheckOutDAO();

    }

    public Result Add(CheckOut checkOut) {

        try {
            if (checkOutDAO.getCheckOut(checkOut.getBookingID(), checkOut.getRoomID()) != null) {
                return new Result("CheckOut already exists !", Result.ACCEPTED);

            } else {
                checkOutDAO.addCheckOut(checkOut);
                return new Result("Add checkOut sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

    public Result Delete(String bookingID, String roomID) {

        try {
            CheckOut checkOut = checkOutDAO.getCheckOut(bookingID, roomID);
            if (checkOut == null) {
                return new Result("CheckOut not found !", Result.ACCEPTED);

            } else {
                checkOutDAO.deleteCheckOut(bookingID, roomID);
                return new Result("Deleted CheckOut !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);
        }

    }

    public Result Update(CheckOut checkOut) {
        try {
            if (checkOutDAO.getCheckOut(checkOut.getBookingID(), checkOut.getRoomID()) == null) {
                return new Result("CheckOut not exists !", Result.ACCEPTED);

            } else {
                checkOutDAO.updateCheckOut(checkOut);
                return new Result("Update checkOut sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);
        }
    }

    public Result Get_by_ID(String bookingID, String roomID) {
        CheckOut checkOut = null;
        try {
            checkOut = checkOutDAO.getCheckOut(bookingID, roomID);
            if (checkOut == null) {
                return new Result("CheckOut not exists !!", Result.ACCEPTED);
            } else {
                return new Result("Get checkOut sucessed!", Result.OK,checkOut);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

}
