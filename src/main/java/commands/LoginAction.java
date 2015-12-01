package commands;

import ents.Cart;
import ents.User;
import manager.CartManager;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginAction extends Action {

    private UserManager userManager = new UserManager();
    private CartManager cartManager = new CartManager();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String res = "login.jsp";

        try {
            User user = userManager.login(login, password);
            if (user != null) {
                res = "hello.jsp";
                req.getSession().setAttribute("user", user);

                Cart cart = cartManager.getByUserId(user.getId());
                req.getSession().setAttribute("cart", cart);
            } else {
                req.setAttribute("message", "Invalid login or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
