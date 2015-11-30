package commands;

import ents.User;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 11/30/15.
 */
public class BanAction extends Action {

    private UserManager userManager = new UserManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "users.jsp";

        String userLogin = request.getParameter("login");

        try {
            User user = userManager.get(userLogin);
            if (user != null) {
                user.setRole(User.Role.BLOCKED);
                userManager.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
