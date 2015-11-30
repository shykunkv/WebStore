package commands;

import ents.Cart;
import ents.CartItem;
import ents.Product;
import ents.User;
import manager.CartItemManager;
import manager.CartManager;
import manager.ProductManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AddToCartAction extends Action {

    private CartManager cartManager = new CartManager();
    private CartItemManager cartItemManager = new CartItemManager();
    private ProductManager productManager = new ProductManager();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User curUser = (User) request.getSession().getAttribute("user");
        Cart curCart = (Cart) request.getSession().getAttribute("cart");
        String res = "cart.jsp";

        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            if (curCart == null) {
                curCart = cartManager.create(curUser.getId(), new Date());
            }
            CartItem cartItem = cartItemManager.create(productId, curCart.getId(), 1);
            Product product = productManager.getById(productId);
            cartItem.setProduct(product);
            curCart.getCartItems().add(cartItem);
            request.getSession().setAttribute("cart", curCart);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
