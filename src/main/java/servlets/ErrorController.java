package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/error")
public class ErrorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String path = "i18n.webstore";
        String curLan = (String) request.getSession().getAttribute("language");

        if (curLan != null && !curLan.equals("en")) {
            path += "_" + curLan;
        }

        ResourceBundle rb = ResourceBundle.getBundle(path);

        request.setAttribute("message", rb.getString("error.problems"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
        requestDispatcher.forward(request, response);
    }

}