package commands;

import ents.User;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

}
