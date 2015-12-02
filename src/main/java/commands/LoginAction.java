package commands;

import ents.Cart;
import ents.User;
import manager.CartManager;
import manager.UserManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This action control's login users to web store
 * Handle 'log in' button on the login page
 */
public class LoginAction extends Action {


    /**
     * Manager that provide work with database (user table)
     */
    private UserManager userManager = new UserManager();

    /**
     * Manager that provide work with database (cart table)
     */
    private CartManager cartManager = new CartManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String res = "login.jsp";

        try {
            User user = userManager.login(login, password);

            if (user != null && user.getRole() != User.Role.BLOCKED) {
                res = "hello.jsp";
                req.getSession().setAttribute("user", user);

                Cart cart = cartManager.getByUserId(user.getId());
                req.getSession().setAttribute("cart", cart);
            } else if (user != null && user.getRole() == User.Role.BLOCKED) { // if user is blocked
                req.setAttribute("message", "Sorry, you are blocked ;( Please contact us with 'shykunkv@gmail.com' if you have soem questions.");
            } else {
                req.setAttribute("message", "Invalid login or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}
