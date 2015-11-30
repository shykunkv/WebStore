package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCartItemDao;
import dao.mysql.MySqlDaoFactory;
import ents.Cart;
import ents.CartItem;

import java.sql.Connection;
import java.util.List;

public class CartItemManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();


    public CartItem getById(int cartItemId) {
        try {
            MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
            return cartItemDao.getByPK(cartItemId);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    public void delete(CartItem cartItem) {
        try{
            MySqlCartItemDao cardItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
            cardItemDao.delete(cartItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CartItem create(int productId, int orderId, int quantity) throws Exception {

        try {
            MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
            CartItem cartItem = new CartItem(productId, orderId, quantity);
            return cartItemDao.persist(cartItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(CartItem cartItem) {
        try {
            MySqlCartItemDao cartItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
            cartItemDao.update(cartItem);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<CartItem> getAllFromCard(int cardId) throws Exception {
        try{
            MySqlCartItemDao cardItemDao = (MySqlCartItemDao) factory.getDao(CartItem.class);
            return cardItemDao.getAllFromCard(cardId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
