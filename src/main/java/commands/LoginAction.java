package commands;

import ents.User;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kostya on 24.11.2015.
 */
public class LoginAction extends Action {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String res = "login.jsp";

        try {
            User user = userManager.login(login, password);
            if (user != null) {
                res = "hello.jsp";
                req.setAttribute("login", user.getLogin());
                req.getSession().setAttribute("user", user);
            } else {
                req.setAttribute("message", "Invadil login or password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
