package ents;

import dao.Identified;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart implements Identified<Integer> {


    private int id;
    private int userId;
    private Date createdAt;
    private boolean paid;
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.createdAt = new Date();
    }

    public Cart(int userId, Date createdAt, boolean paid) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.paid = paid;
        this.cartItems = new ArrayList<>();
    }

    public Cart(int id, int userId, Date createdAt, boolean paid) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.paid = paid;
        this.cartItems = new ArrayList<>();
    }

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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
