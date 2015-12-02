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
 * Change user's role to USER (allow him use web store).
 * Handle 'unban' button on the users page.
 * Used only by ADMIN users.
 */
public class UnbanAction extends Action {


    /**
     * Manager that provide work with database (user table)
     */
    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "users.jsp";

        String userLogin = request.getParameter("login");

        try {
            User user = userManager.get(userLogin);
            if (user != null) {
                user.setRole(User.Role.USER);
                userManager.update(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}