package commands;

import ents.Cart;
import ents.CartItem;
import manager.CartItemManager;
import manager.CartManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Checkout action, that delete user's current shopping cart with all products in it
 * Handle 'checkout' button on the shopping cart page.
 */
public class CheckoutAction extends Action {

    /**
     * Manager that provide work with database (cart item table)
     */
    private CartItemManager cartItemManager = new CartItemManager();

    /**
     * Manager that provide work with database (cart table)
     */
    private CartManager cartManager = new CartManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "checkout.jsp";

        try {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            for (CartItem ci : cart.getCartItems()) {
                cartItemManager.delete(ci);
            }
            cartManager.delete(cart);
            request.getSession().removeAttribute("cart");
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
            res = "/error";
        }

        return res;
    }
}
