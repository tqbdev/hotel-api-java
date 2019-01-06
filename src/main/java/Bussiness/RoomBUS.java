/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Room;
import DTO.Room;
import DataAccess.RoomDAO;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class RoomBUS {

    private RoomDAO roomDAO;

    public RoomBUS() {
        roomDAO = new RoomDAO();
    }

     public Result Add(Room room) {
        try {
            if (roomDAO.getRoom(room.getRoomID()) != null) {
                roomDAO.getRoom(room.getRoomID()).ShowInfo();
                return new Result("Room already exists !", Result.ACCEPTED);
            } else {
                roomDAO.addRoom(room);
                return new Result("Add Room sucessed !", Result.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request !", Result.BAD_REQUEST);
        }
    }

    public Result Delete(String roomID) {
        try {
            Room room = roomDAO.getRoom(roomID);
            if (room == null) {
                return new Result("Room not found !", Result.ACCEPTED);
            } else {
                if (room.isActive() == false) {
                    return new Result("Room is in use !", Result.ACCEPTED);
                } else {
                    roomDAO.deleteRoom(roomID);
                    return new Result("Deleted Room !", Result.OK);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request! ", Result.BAD_REQUEST);

        }
    }

    public Result Update(Room room) {
        try {
            if (roomDAO.getRoom(room.getRoomID()) == null) {
                return new Result("Room not exists !", Result.ACCEPTED);

            } else {
                roomDAO.updateRoom(room);
                return new Result("Update room sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

    public Result Get_by_ID(String roomID) {
        Room room = null;
        try {
            room = roomDAO.getRoom(roomID);
            if (room == null) {
                return new Result("Room not exists !", Result.ACCEPTED);

            } else {
                return new Result("Get room sucessed !", Result.OK, room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

}
