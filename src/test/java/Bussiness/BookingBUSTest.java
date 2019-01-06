/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Booking;
import DataAccess.Connecter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author lap10467
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingBUSTest {

    private ArrayList<Booking> ArrayBookings;
    private BookingBUS instance;

    public BookingBUSTest() {
        System.out.println("Install ");
        ArrayBookings = new ArrayList<>();
        ArrayBookings.add(new Booking("1", "1", "1", new Date(), new Date(), new Date(),1));
        ArrayBookings.add(new Booking("2", "2", "2", new Date(), new Date(), new Date(),2));

        instance = new BookingBUS();
    }

    @BeforeClass
    public static void setUp() throws SQLException {

        System.out.println("Setup ");

        Connecter.Switch_to_Test_Database();
        Connection connection = Connecter.Connect();
        try {
            String command = "DELETE FROM Booking WHERE True";
            Statement statement = connection.createStatement();
            statement.execute(command);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        Connecter.CloseConnection();
        Connecter.Switch_to_Main_Database();
    }

    /**
     * Test of Add method, of class BookingBUS.
     */
    @Test
    public void AA_testAdd() {
        System.out.println("Add");
        Result result = instance.Add(ArrayBookings.get(0));
        Result result2 = instance.Add(ArrayBookings.get(1));

        assertTrue(Result.OK == result.getStatus());
        assertTrue(Result.OK == result2.getStatus());
    }

    @Test
    public void AB_testAdd_Duplicate() {
        System.out.println("Add2");
        Result result = instance.Add(ArrayBookings.get(0));
        System.out.println(result.getStatus());
        assertTrue(Result.ACCEPTED == result.getStatus());
    }

    /**
     * Test of Update method, of class BookingBUS.
     */
    @Test
    public void B_testUpdate() {
        Booking booking = ArrayBookings.get(0);
        booking.setCustomerID("2");
        Result result = instance.Update(booking);
        assertTrue(Result.OK == result.getStatus());

    }

    /**
     * Test of Get_by_ID method, of class BookingBUS.
     */
    @Test
    public void C_testGet_by_ID() {
        System.out.println("Get_by_ID");
        String bookingID = ArrayBookings.get(1).getBookingID();
        Result result = instance.Get_by_ID(bookingID);
        Booking booking2 = ArrayBookings.get(1);
        assertEquals(result.getObject(), booking2);
    }

    /**
     * Test of Delete method, of class BookingBUS.
     */
    @Test
    public void D_testDelete() {
        System.out.println("Delete");
        String bookingID = ArrayBookings.get(0).getBookingID();
        Result result = instance.Delete(bookingID);
        assertTrue(Result.OK == result.getStatus());

    }
}
