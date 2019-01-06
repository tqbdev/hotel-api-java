/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Bussiness.BookingBUS;
import Bussiness.Result;
import Bussiness.BookingBUS;
import DTO.Booking;
import DTO.Booking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lap10467
 */
public class BookingServlet extends HttpServlet {
 Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    BookingBUS bookingBUS = new BookingBUS();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Booking booking = gson.fromJson(req.getReader(), Booking.class);
        Result result=  bookingBUS.Add(booking);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ID = req.getParameter("bookingID");
   Result result=       bookingBUS.Delete(ID);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = gson.fromJson(req.getReader(), Booking.class);
    Result result=           bookingBUS.Update(booking);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("bookingID");
        Result result = bookingBUS.Get_by_ID(ID);
        resp.setStatus(result.getStatus());
        if (result.getObject() != null) {
            resp.getWriter().write(gson.toJson(result.getObject()));
        } else {
            resp.getWriter().write(gson.toJson(result));
        }

    }
}
