/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Bussiness.MenuBUS;
import Bussiness.Result;
import DTO.Menu;
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
public class MenuServlet extends HttpServlet {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    MenuBUS menuBUS = new MenuBUS();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Menu menu = gson.fromJson(req.getReader(), Menu.class);
        Result result=  menuBUS.Add(menu);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ID = req.getParameter("priceID");
   Result result=       menuBUS.Delete(ID);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Menu menu = gson.fromJson(req.getReader(), Menu.class);
    Result result=           menuBUS.Update(menu);
        resp.setStatus(result.getStatus());
        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("priceID");
        Result result = menuBUS.Get_by_ID(ID);
        resp.setStatus(result.getStatus());
        if (result.getObject() != null) {
            resp.getWriter().write(gson.toJson(result.getObject()));
        } else {
            resp.getWriter().write(gson.toJson(result));
        }

    }
}
