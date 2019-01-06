/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DTO.CheckOut;
import DataAccess.Connecter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class CheckOutDAO {

    private static final String TABLE_NAME = "CheckOut";
    private static final String BOOKING_ID_COL = "bookingID";
    private static final String ROOM_ID_COL = "roomID";
    private static final String TOTAL_AMOUNT_COL = "totalAmount";
    private static final String CHECK_OUT_DATE_COL = "checkOutDate";

    Connection connection = null;

    public CheckOutDAO() {
    }

    //add checkOut
    public int addCheckOut(CheckOut checkOut) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "INSERT INTO " + TABLE_NAME + " values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, checkOut.getBookingID());
            preparedStatement.setString(2, checkOut.getRoomID());
            preparedStatement.setLong(3, checkOut.getTotalAmount());
            preparedStatement.setDate(4, new java.sql.Date(checkOut.getCheckOutDate().getTime()));
            int ret = preparedStatement.executeUpdate();
            connection.commit();
            return ret;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

    //delete CheckOut
    public int deleteCheckOut(String bookingID,String roomID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "DELETE FROM " + TABLE_NAME + " WHERE " + BOOKING_ID_COL + " = ?"
                   + " AND "+ROOM_ID_COL+" =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, bookingID);
            preparedStatement.setString(2, roomID);
            int ret = preparedStatement.executeUpdate();
            connection.commit();
            return ret;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

    //update CheckOut
    public int updateCheckOut(CheckOut checkOut) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "UPDATE " + TABLE_NAME
                    + " SET " + ROOM_ID_COL + " = ? , "
                    + TOTAL_AMOUNT_COL + " = ? , "
                    + CHECK_OUT_DATE_COL + " = ? "
                    + " WHERE " + BOOKING_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(4, checkOut.getBookingID());
            preparedStatement.setString(1, checkOut.getRoomID());
            preparedStatement.setLong(2, checkOut.getTotalAmount());
            preparedStatement.setDate(3, new java.sql.Date(checkOut.getCheckOutDate().getTime()));
            int ret = preparedStatement.executeUpdate();
            connection.commit();
            return ret;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

    //get CheckOut
    public CheckOut getCheckOut(String bookingID,String roomID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "SELECT * FROM " + TABLE_NAME + " WHERE " + BOOKING_ID_COL + " = ?" +" AND "+ROOM_ID_COL+" =? " ;
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1,bookingID);
            preparedStatement.setString(2, roomID);
            
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();

            CheckOut checkOut = null;
            if (rs.next()) {
                checkOut = new CheckOut(rs.getString(1),rs.getString(2),rs.getLong(3),rs.getDate(4));
            }
            return checkOut;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }
}
