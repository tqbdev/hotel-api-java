/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Bussiness.CheckOutBUS;
import Bussiness.Result;
import Bussiness.CheckOutBUS;
import DTO.CheckOut;
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
public class CheckOutServlet extends HttpServlet {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    CheckOutBUS checkOutBUS = new CheckOutBUS();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CheckOut checkOut = gson.fromJson(req.getReader(), CheckOut.class);
        Result result = checkOutBUS.Add(checkOut);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bookingID = req.getParameter("bookingID");
        String roomID = req.getParameter("roomID");
        Result result = checkOutBUS.Delete(bookingID, roomID);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckOut checkOut = gson.fromJson(req.getReader(), CheckOut.class);
        Result result = checkOutBUS.Update(checkOut);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookingID = req.getParameter("bookingID");
        String roomID = req.getParameter("roomID");
        Result result = checkOutBUS.Get_by_ID(bookingID, roomID);
        resp.setStatus(result.getStatus());
        if (result.getObject() != null) {
            resp.getWriter().write(gson.toJson(result.getObject()));
        } else {
            resp.getWriter().write(gson.toJson(result));
        }

    }

}
