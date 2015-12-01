package commands;

import ents.Cart;
import ents.CartItem;
import manager.CartItemManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateCartAction extends Action {

    private CartItemManager cartItemManager = new CartItemManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String res = "cart.jsp";

        Cart curCart = (Cart) request.getSession().getAttribute("cart");

        try {
            int cartItemId = Integer.parseInt(request.getParameter("item_id"));
            int newQuantity = Integer.parseInt(request.getParameter("quantity"));

            for (CartItem ci: curCart.getCartItems()) {
                if (ci.getId() == cartItemId) {
                    ci.setQuantity(newQuantity);
                    cartItemManager.update(ci);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
