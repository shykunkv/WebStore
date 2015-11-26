package commands;

import ents.User;
import manager.UserManager;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kostya on 26.11.2015.
 */
public class RegisterAction extends Action {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private  static final String MAIL_PARAM = "mail";


    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String mail = req.getParameter(MAIL_PARAM);

        String res = "error.jsp";

        User user = null;

        try {
            user = userManager.get(login);
            if (user == null) {
                user = userManager.create(login, password, mail);
                res = "index.jsp";
            } else {
                req.setAttribute("message", "User already exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

}
