package ents;


import dao.Identified;

public class User implements Identified<Integer> {


    public static enum Role {
        USER, ADMIN, BLOCKED;
    }

    private int id;
    private String login;
    private String hash;
    private String mail;
    private Role role;



    public User() {
    }

    public User(String login, String hash, String mail) {
        this.login = login;
        this.hash = hash;
        this.mail = mail;
        this.role = Role.USER;
    }

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

