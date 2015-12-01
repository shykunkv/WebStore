package ents;

import dao.Identified;


/**
 * Entity, that represent item in shopping cart
 */
public class CartItem implements Identified<Integer> {

    /**
     * Unique id of this item, PK in database
     */
    private int id;

    /**
     * Id of product in shopping cart
     */
    private int productId;

    /**
     * Quantity if product in shopping cart
     */
    private int quantity;

    /**
     * Belong to shipping cart with this id
     */
    private int orderId;

    /**
     * Product from this cart item
     */
    private Product product = null;


    /**
     *  Constructors
     */
    public CartItem() {
    }

    public CartItem(int productId, int orderId, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public CartItem(int id, int productId, int orderId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }


    /**
     * Getters and setters
     */

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
