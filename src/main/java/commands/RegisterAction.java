package commands;

import ents.User;
import manager.UserManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Perform register (creating) new user in system.
 * Handle 'Sign up' button on the registration page
 */
public class RegisterAction extends Action {

    /**
     * Manager that provide work with database (user table)
     */
    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //get parameters for new user from jsp inputs
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");

        String res = "register.jsp";

        User user;

        try {

            user = userManager.get(login);
            if (user == null) { //if there no user with this login
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
