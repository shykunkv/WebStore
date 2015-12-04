package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCartDao;
import dao.mysql.MySqlCartItemDao;
import dao.mysql.MySqlDaoFactory;
import ents.Cart;
import ents.CartItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Perform all available functions with cart objects
 */
public class CartManager {

    /**
     * Dao objects factory
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Remove record from data source for some specific cart
     * @param cart cart to remove
     * @throws SQLException
     */
    public void delete(Cart cart) throws SQLException {
        MySqlCartDao cardDao = (MySqlCartDao) factory.getDao(Cart.class);
        cardDao.delete(cart);
    }

    /**
     * Create new record in data source with specific params
     * @param userId cart refers to user with this id
     * @param createdAt date of creations
     * @return created cart object
     * @throws SQLException
     */
    public Cart create(int userId, Date createdAt) throws SQLException {
        MySqlCartDao cardDao = (MySqlCartDao) factory.getDao(Cart.class);
        Cart cart = new Cart(userId, createdAt);
        return cardDao.persist(cart);
    }

    /**
     * Get cart from datasource whish refers to some specific user
     * @param userId id of the user, which refers for
     * @return cart for tgis user
     * @throws SQLException
     */
    public Cart getByUserId(int userId) throws SQLException {
        MySqlCartDao cardDao = (MySqlCartDao) factory.getDao(Cart.class);
        Cart cart = cardDao.getByUserId(userId);

        if (cart != null) { //if cart exist - get all cart items for it

            CartItemManager cartItemManager = new CartItemManager();
            List<CartItem> cartItemList = cartItemManager.getAllFromCard(cart.getId());
            if (cartItemList.size() > 0) {
                ProductManager productManager = new ProductManager();
                for (CartItem ci : cartItemList) {
                    ci.setProduct(productManager.getById(ci.getProductId()));
                }
                cart.setCartItems(cartItemList);

            }
        }

        return cart;
    }

}
