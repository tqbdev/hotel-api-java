/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DTO.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class RoomDAO {

    private static final String TABLE_NAME = "Room";
    private static final String ROOM_ID_COL = "roomID";
    private static final String ROOM_NAME_COL = "roomName";
    private static final String ROOM_TYPE_COL = "roomType";
    private static final String CREATE_DATE_COL = "createDate";
    private static final String IS_ACTIVE_COL = "isActive";
    private static final String DESCRIPTION_COL = "description";

    Connection connection = null;

    public RoomDAO() {
    }

    //add Room
    public int addRoom(Room room) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "INSERT INTO " + TABLE_NAME + " values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, room.getRoomID());
            preparedStatement.setString(2, room.getRoomName());
            preparedStatement.setInt(3, room.getRoomType());
            preparedStatement.setDate(4, new java.sql.Date(room.getCreateDate().getTime()));
            preparedStatement.setBoolean(5, room.isActive());
            preparedStatement.setString(6, room.getDescription());
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

    //delete Room
    public int deleteRoom(String roomID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "DELETE FROM " + TABLE_NAME + " WHERE " + ROOM_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, roomID);
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

    //update Room
    public int updateRoom(Room room) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "UPDATE " + TABLE_NAME
                    + " SET " + ROOM_NAME_COL + " = ? , "
                    + ROOM_TYPE_COL + " = ? , "
                    + CREATE_DATE_COL + " = ? , "
                    + IS_ACTIVE_COL + " = ? , "
                    + DESCRIPTION_COL + " = ?  "
                    + " WHERE " + ROOM_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(6, room.getRoomID());
            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getRoomType());
            preparedStatement.setDate(3, new java.sql.Date(room.getCreateDate().getTime()));
            preparedStatement.setBoolean(4, room.isActive());
            preparedStatement.setString(5, room.getDescription());
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

    //get room
    public Room getRoom(String roomID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "SELECT * FROM " + TABLE_NAME + " WHERE " + ROOM_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();

            Room room = null;
            if (rs.next()) {
                room = new Room(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getBoolean(5), rs.getString(6));
            }
            return room;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

}
