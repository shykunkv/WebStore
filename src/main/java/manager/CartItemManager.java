package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCartItemDao;
import dao.mysql.MySqlDaoFactory;
import ents.Cart;
import ents.CartItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartItemManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();


    public CartItem getById(int cartItemId) throws SQLException {
        MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        return cartItemDao.getByPK(cartItemId);
    }

    public void delete(CartItem cartItem) throws SQLException {
        MySqlCartItemDao cardItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        cardItemDao.delete(cartItem);
    }

    public CartItem create(int productId, int orderId, int quantity) throws SQLException {
        MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        CartItem cartItem = new CartItem(productId, orderId, quantity);

        return cartItemDao.persist(cartItem);
    }

    public void update(CartItem cartItem) throws SQLException {
        MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
        cartItemDao.update(cartItem);
    }

    List<CartItem> getAllFromCard(int cardId) throws SQLException {
        MySqlCartItemDao cardItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);

        return cardItemDao.getAllFromCard(cardId);
    }
}
