
import Handler.BookingServlet;
import Handler.ChargingServlet;
import Handler.CheckOutServlet;
import Handler.CustomerServlet;
import Handler.MenuServlet;
import Handler.RoomServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {

    public static void main(String[] args) {

        try {
            Server server = new Server(7070);

            ServletContextHandler handler = new ServletContextHandler();
            server.setHandler(handler);

            handler.addServlet(RoomServlet.class, "/room");
            handler.addServlet(MenuServlet.class, "/menu");
            handler.addServlet(CustomerServlet.class, "/customer");
            handler.addServlet(BookingServlet.class, "/booking");
            handler.addServlet(CheckOutServlet.class, "/checkout");
            handler.addServlet(ChargingServlet.class, "/charging");
            server.start();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
