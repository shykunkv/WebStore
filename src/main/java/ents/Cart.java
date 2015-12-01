package ents;

import dao.Identified;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Entity, represent shopping cart for register user
 */
public class Cart implements Identified<Integer> {

    /**
     * Shopping cart id, unique, PK in database
     */
    private int id;

    /**
     * Owned by user with this id
     */
    private int userId;

    /**
     * Date when shopping cart was created
     */
    private Date createdAt;

    /**
     * List of items in this order: [product, quantity]
     */
    private List<CartItem> cartItems;

    private int size;

    /**
     * Constructors
     */
    public Cart() {
        this.cartItems = new ArrayList<>();
        this.createdAt = new Date();
    }

    public Cart(int userId, Date createdAt) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.cartItems = new ArrayList<>();
    }

    public Cart(int id, int userId, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.cartItems = new ArrayList<>();
    }


    /**
     * Get number of items in this cart
     * @return size of cart
     */
    public int getSize() {
        return this.cartItems.size();
    }


    /**
     * Getters and setters
     */

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
