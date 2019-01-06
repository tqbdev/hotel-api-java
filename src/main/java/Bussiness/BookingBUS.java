/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Booking;
import DataAccess.BookingDAO;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class BookingBUS  {



    private BookingDAO bookingDAO;

    public BookingBUS() {
        bookingDAO = new BookingDAO();
    }

   public Result Add(Booking booking) {
        try {
            if (bookingDAO.getBooking(booking.getBookingID()) != null) {
                return new Result("Booking already exists !", Result.ACCEPTED);
            } else {
                bookingDAO.addBooking(booking);
                return new Result("Add Booking sucessed !", Result.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request !", Result.BAD_REQUEST);
        }
    }

    public Result Delete(String bookingID) {
        try {
            Booking booking = bookingDAO.getBooking(bookingID);
            if (booking == null) {
                return new Result("Booking not found !", Result.ACCEPTED);
            } else {
                if (booking.getBookingStatus()== 0) {
                    return new Result("Booking is in use !", Result.ACCEPTED);
                } else {
                    bookingDAO.deleteBooking(bookingID);
                    return new Result("Deleted Booking !", Result.OK);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request! ", Result.BAD_REQUEST);

        }
    }

    public Result Update(Booking booking) {
        try {
            if (bookingDAO.getBooking(booking.getBookingID()) == null) {
                return new Result("Booking not exists !", Result.ACCEPTED);

            } else {
                bookingDAO.updateBooking(booking);
                return new Result("Update booking sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

    public Result Get_by_ID(String bookingID) {
        Booking booking = null;
        try {
            booking = bookingDAO.getBooking(bookingID);
            if (booking == null) {
                return new Result("Booking not exists !", Result.ACCEPTED);

            } else {
                return new Result("Get booking sucessed !", Result.OK, booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }


}
