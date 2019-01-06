package Handler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Bussiness.RoomBUS;
import Bussiness.Result;
import Bussiness.RoomBUS;
import DTO.Room;
import DTO.Room;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lap10467
 */
public class RoomServlet extends HttpServlet {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    RoomBUS roomBUS = new RoomBUS();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Room room = gson.fromJson(req.getReader(), Room.class);
        Result result=  roomBUS.Add(room);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ID = req.getParameter("roomID");
   Result result=       roomBUS.Delete(ID);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = gson.fromJson(req.getReader(), Room.class);
    Result result=           roomBUS.Update(room);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("roomID");
        Result result = roomBUS.Get_by_ID(ID);
        resp.setStatus(result.getStatus());
        if (result.getObject() != null) {
            resp.getWriter().write(gson.toJson(result.getObject()));
        } else {
            resp.getWriter().write(gson.toJson(result));
        }

    }
}
