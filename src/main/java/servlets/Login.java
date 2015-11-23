package servlets;

import DbTools.ConnectionManager;
import com.sun.deploy.net.HttpResponse;
import ents.User;
import repo.UserRepo;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        // create static login parameters
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //String mail = req.getParameter("mail");

        User user = UserRepo.getUser(ConnectionManager.getInstance(), "login", login);

        if (user == null) {
            out.println("incorrect login");
            return;
        }

        try {
            if (!HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
                out.println("Incorrect password or login!");
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            return;
        } catch (InvalidKeySpecException e) {
            return;
        }

        req.getSession().setAttribute("name", user.getLogin());
        resp.sendRedirect("hello.jsp");
    }

//    private String login(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//    }
}
