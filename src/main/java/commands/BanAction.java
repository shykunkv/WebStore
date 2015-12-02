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
 * Change user's role to BLOCKED
 * Handle 'Ban' button on the ADMIN user page
 */
public class BanAction extends Action {


    /**
     * Manager that provide work with database (user table)
     */
    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String res = "users.jsp";

        try {
            String userLogin = request.getParameter("login"); //get login of the user to ban

            User user = userManager.get(userLogin);
            if (user != null) {
                user.setRole(User.Role.BLOCKED);
                userManager.update(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}
