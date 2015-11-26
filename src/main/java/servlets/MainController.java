package servlets;

import commands.ActionInvoker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/main")
public class MainController extends HttpServlet {


    private static final ActionInvoker actionInvoker = ActionInvoker.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String page = null;
        try {
            page = actionInvoker.invoke(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            //Logger.getLogger(this.getClass()).error(e.getMessage());
            page = "error.jsp";
        } catch (IOException e) {
            e.printStackTrace();
            //Logger.getLogger(this.getClass()).error(e.getMessage());
            page = "error.jsp";
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
