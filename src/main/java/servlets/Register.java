package servlets;

import dao.ConnectionManager;
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


@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");
        PrintWriter out = resp.getWriter();

        User user = UserRepo.getUser(ConnectionManager.getInstance(), "login", login);

        if (user != null) {
            out.println("Error");
            return;
        }

        user = new User();
        user.setLogin(login);
        user.setMail(mail);
        try {
            user.setHash(HashUtil.createHash(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        UserRepo.addUser(ConnectionManager.getInstance(), user);
        resp.sendRedirect("index.jsp");
    }
}
