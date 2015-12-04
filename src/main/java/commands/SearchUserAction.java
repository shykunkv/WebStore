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
 * Search user with login in system.
 * Handle 'search' button on the users page.
 * Used only by ADMIN users.
 */
public class SearchUserAction extends Action {

    /**
     * Manager that provide work with database (user table)
     */
    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String res = "users.jsp";


        String login = request.getParameter("login");
        try {
            User user = userManager.get(login);
            if (user != null) {
                request.setAttribute("founded_user", user);
            } else {
                request.setAttribute("message", "not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }

}
