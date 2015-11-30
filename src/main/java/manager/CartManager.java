package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCartDao;
import dao.mysql.MySqlCartItemDao;
import dao.mysql.MySqlDaoFactory;
import ents.Cart;
import ents.CartItem;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class CartManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    public void delete(Cart cart) {
        try{
            MySqlCartDao cardDao = (MySqlCartDao) factory.getDao(Cart.class);
            cardDao.delete(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Cart create(int userId, Date createdAt) throws Exception {
        try {
            MySqlCartDao cardDao = (MySqlCartDao) factory.getDao(Cart.class);
            Cart cart = new Cart(userId, createdAt, true);
            return cardDao.persist(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Cart getByUserId(int userId) throws Exception {
        try {
            MySqlCartDao cardDao = (MySqlCartDao) factory.getDao(Cart.class);
            Cart cart = cardDao.getByUserId(userId);

            if (cart != null) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
