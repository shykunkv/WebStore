package ents;

import dao.Identified;

/**
 * Entity, that represent product in web store
 */
public class Product implements Identified<Integer> {

    /**
     * Unique id for product, PK in database
     */
    private  int id;

    /**
     * Product name
     */
    private String name;

    /**
     * Product brand
     */
    private String brand;

    /**
     * Product price in $
     */
    private double price;

    /**
     * Product quantity in stock
     */
    private int quantity;

    /**
     * Product description
     */
    private  String description;

    /**
     * Product belong to category with this id
     */
    private int categoryId;

    /**
     * URL to product image
     */
    private String image;


    /**
     * Constructors
     */
    public Product() {
    }

    public Product(String name, String brand, double price, int quantity, String description, int categoryId, String image) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryId = categoryId;
        this.image = image;
    }

    public Product(int id, String name, String brand, double price, int quantity, String description, int categoryId, String image) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryId = categoryId;
        this.image = image;
    }


    /**
     * Getters and Setters
     */

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
