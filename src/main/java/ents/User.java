package ents;

import dao.Identified;


/**
 * Entity, that represent user in web store
 */
public class User implements Identified<Integer> {

    /**
     * Possible roles in web store:
     * USER - common user of web store. Can browse categories, products, add them in shopping cart, etc.
     * ADMIN - administrator of web store. Can block/unblock users, add new products, etc.
     * BLOCKED - blocked user for some reasons, with no access to web store.
     */
    public static enum Role {
        USER, ADMIN, BLOCKED;
    }

    /**
     * Unique user id, PK in database
     */
    private int id;

    /**
     * User login, used in authorization
     */
    private String login;

    /**
     * User hashing password, used in authorization
     */
    private String hash;

    /**
     * User email address
     */
    private String mail;

    /**
     * User role (ADMIN, USER, BLOCKED)
     */
    private Role role;


    /**
     * Constructors
     */
    public User() {
    }

    public User(String login, String hash, String mail) {
        this.login = login;
        this.hash = hash;
        this.mail = mail;
        this.role = Role.USER;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getLogin() {
        return login;
    }



    public void setLogin(String login) {
        this.login = login;
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}

