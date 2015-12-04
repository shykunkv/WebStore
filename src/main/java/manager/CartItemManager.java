package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCartItemDao;
import dao.mysql.MySqlDaoFactory;
import ents.Cart;
import ents.CartItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Perform all available functions with cart item objects
 */
public class CartItemManager {

    /**
     * Dao objects factory
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Get cart item from data source with specific id
     * @param cartItemId cart item id
     * @return cart item with specific id
     * @throws SQLException
     */
    public CartItem getById(int cartItemId) throws SQLException {
        MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        return cartItemDao.getByPK(cartItemId);
    }

    /**
     * Remove record from data source for specific cart item
     * @param cartItem cart item to remove
     * @throws SQLException
     */
    public void delete(CartItem cartItem) throws SQLException {
        MySqlCartItemDao cardItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        cardItemDao.delete(cartItem);
    }

    /**
     * Create new record in data source with specific params
     * @param productId refers product id
     * @param orderId refers cart id
     * @param quantity product quantity
     * @return created cart item object
     * @throws SQLException
     */
    public CartItem create(int productId, int orderId, int quantity) throws SQLException {
        MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        CartItem cartItem = new CartItem(productId, orderId, quantity);

        return cartItemDao.persist(cartItem);
    }

    /**
     * Update record in data source for specific cart item
     * @param cartItem  cart item to update
     * @throws SQLException
     */
    public void update(CartItem cartItem) throws SQLException {
        MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        cartItemDao.update(cartItem);
    }

    /**
     * Get all cart items, that refers to some cart with specific id
     * @param cardId cart id
     * @return list of cart items for this cart
     * @throws SQLException
     */
    List<CartItem> getAllFromCard(int cardId) throws SQLException {
        MySqlCartItemDao cardItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);

        return cardItemDao.getAllFromCard(cardId);
    }
}
