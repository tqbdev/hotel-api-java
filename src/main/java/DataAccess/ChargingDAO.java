/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DTO.Charging;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class ChargingDAO {

    private static final String TABLE_NAME = "Charging";
    private static final String CHARGE_ID_COL = "chargeID";
    private static final String BOOKING_ID_COL = "bookingID";
    private static final String TOTAL_AMOUNT_COL = "totalAmount";
    private static final String CHARGE_DATE_COL = "chargeDate";
    private static final String TPE_TRANS_ID_COL = "tpeTransID";
    private static final String TPE_RETURN_CODE_COL = "tpeReturnCode";
    private static final String CHARGE_STATUS_COL = "chargeStatus";

    Connection connection = null;

    public ChargingDAO() {
    }

    //add Chargin
    public int addChargin(Charging charging) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "INSERT INTO " + TABLE_NAME + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, charging.getChargeID());
            preparedStatement.setString(2, charging.getBookingID());
            preparedStatement.setLong(3, charging.getTotalAmount());
            preparedStatement.setDate(4, new java.sql.Date(charging.getChargeDate().getTime()));
            preparedStatement.setLong(5, charging.getTpeTransID());
            preparedStatement.setInt(6, charging.getTpeReturnCode());
            preparedStatement.setInt(7, charging.getChargeStatus());

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

    //delete Charging
    public int deleteCharing(String ID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "DELETE FROM " + TABLE_NAME + " WHERE " + CHARGE_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, ID);
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

    //update Charging
    public int updateCharging(Charging charging) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "UPDATE " + TABLE_NAME
                    + " SET " + BOOKING_ID_COL + " = ? , "
                    + TOTAL_AMOUNT_COL + " = ? , "
                    + CHARGE_DATE_COL + " = ? , "
                    + TPE_TRANS_ID_COL + " = ? , "
                    + TPE_RETURN_CODE_COL + " = ?, "
                    + CHARGE_STATUS_COL + " = ? "
                    + " WHERE " + CHARGE_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(7, charging.getChargeID());
            preparedStatement.setString(1, charging.getBookingID());
            preparedStatement.setLong(2, charging.getTotalAmount());
            preparedStatement.setDate(3, new java.sql.Date(charging.getChargeDate().getTime()));
            preparedStatement.setLong(4, charging.getTpeTransID());
            preparedStatement.setInt(5, charging.getTpeReturnCode());
            preparedStatement.setInt(6, charging.getChargeStatus());
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

    //get Charging
    public Charging getCharging(String chargeID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "SELECT * FROM " + TABLE_NAME + " WHERE " + CHARGE_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, chargeID);
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();

            Charging charging = null;
            if (rs.next()) {
                charging = new Charging(rs.getString(1), rs.getString(2),
                        rs.getLong(3), rs.getDate(4), rs.getLong(5), rs.getInt(6), rs.getInt(7));
            }
            return charging;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }
}
