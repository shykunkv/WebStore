package commands;

import ents.Cart;
import ents.CartItem;
import ents.Product;
import ents.User;
import manager.CartItemManager;
import manager.CartManager;
import manager.ProductManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;


/**
 * Add some new product to shopping cart. Create new cart, if current cart is null
 * Handle 'Add to cart' button on the product page. Used by all users.
 */
public class AddToCartAction extends Action {

    /**
     * Manager that provide work with database (cart table)
     */
    private CartManager cartManager = new CartManager();

    /**
     * Manager that provide work with database (cart item table)
     */
    private CartItemManager cartItemManager = new CartItemManager();

    /**
     * Manager that provide work with database (product table)
     */
    private ProductManager productManager = new ProductManager();


    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User curUser = (User) request.getSession().getAttribute("user"); //get current user from session
        Cart curCart = (Cart) request.getSession().getAttribute("cart"); //get current shopping cart from session
        String res = "cart.jsp";

        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            if (curCart == null) { //if we still has no cart - create new
                curCart = cartManager.create(curUser.getId(), new Date());
            }
            CartItem cartItem = cartItemManager.create(productId, curCart.getId(), 1);
            Product product = productManager.getById(productId);
            cartItem.setProduct(product);
            curCart.getCartItems().add(cartItem);
            request.getSession().setAttribute("cart", curCart);
        } catch (SQLException e) {
            res = "/error";
            e.printStackTrace();
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        return res;
    }
}
