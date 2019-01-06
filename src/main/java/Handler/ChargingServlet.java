/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Bussiness.ChargingBUS;
import Bussiness.Result;
import Bussiness.ChargingBUS;
import DTO.Charging;
import DTO.Charging;
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
public class ChargingServlet extends HttpServlet {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    ChargingBUS chargingBUS = new ChargingBUS();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Charging charging = gson.fromJson(req.getReader(), Charging.class);
        Result result = chargingBUS.Add(charging);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ID = req.getParameter("chargeID");
        Result result = chargingBUS.Delete(ID);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Charging charging = gson.fromJson(req.getReader(), Charging.class);
        Result result = chargingBUS.Update(charging);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("chargeID");
        Result result = chargingBUS.Get_by_ID(ID);
        resp.setStatus(result.getStatus());
        if (result.getObject() != null) {
            resp.getWriter().write(gson.toJson(result.getObject()));
        } else {
            resp.getWriter().write(gson.toJson(result));
        }

    }

}
