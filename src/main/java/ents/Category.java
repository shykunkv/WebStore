package ents;

import dao.Identified;


/**
 * Entity, that represent category for products
 * 'Computers', 'Bicycles', etc.
 */
public class Category implements Identified<Integer> {

    /**
     * Unique category id, PK in database
     */
    private int id;

    /**
     * Category name
     */
    private String name;

    /**
     * Categgory description
     */
    private String description;


    /**
     * Constructors
     */
    public Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
