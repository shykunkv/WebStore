package commands;

import ents.Cart;
import ents.CartItem;
import ents.User;
import manager.CartItemManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteItemAction extends Action {

    private CartItemManager cartItemManager = new CartItemManager();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "cart.jsp";

        try {
            int cartItemId = Integer.parseInt(request.getParameter("item_id"));

            Cart cart = (Cart) request.getSession().getAttribute("cart");

            for (CartItem ci: cart.getCartItems()) {
                if (ci.getId() == cartItemId) {
                    cart.getCartItems().remove(ci);
                    cartItemManager.delete(ci);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
