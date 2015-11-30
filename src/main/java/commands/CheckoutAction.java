package commands;

import ents.Cart;
import ents.CartItem;
import ents.User;
import manager.CartItemManager;
import manager.CartManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CheckoutAction extends Action {

    private CartItemManager cartItemManager = new CartItemManager();
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
