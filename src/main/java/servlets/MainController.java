package servlets;

import commands.ActionInvoker;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Main servlet, control all actions in system
 */
@WebServlet("/main")
public class MainController extends HttpServlet {

    /**
     * Invoker that decides which of the available actions will be used
     */
    private static final ActionInvoker actionInvoker = ActionInvoker.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Handle get and post request for main servlet
     * @param req request from view
     * @param resp response from servlet
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * Page that will be user next
         */
        String page;

        try {
            page = actionInvoker.invoke(req, resp);
            Logger.getLogger(this.getClass()).info("page: "+ page);
        } catch (ServletException e) {
            e.printStackTrace();
            Logger.getLogger(this.getClass()).error(e.getMessage());
            page = "error.jsp";
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(this.getClass()).error(e.getMessage());
            page = "error.jsp";
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
