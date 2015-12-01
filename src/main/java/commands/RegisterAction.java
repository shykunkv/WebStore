package commands;

import ents.User;
import manager.UserManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class RegisterAction extends Action {


    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");

        String res = "register.jsp";

        User user;

        try {
            user = userManager.get(login);
            if (user == null) {
                user = userManager.create(login, password, mail);
                res = "login.jsp";
            } else {
                req.setAttribute("login_message", "Already used login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }

}
