package ents;


public class User {

    private int id;
    private String login;
    private String hash;
    private String mail;

    public User() {
    }

    public User(int id, String login, String hash, String mail) {
        this.id = id;
        this.login = login;
        this.hash = hash;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }


    public void setId(int id) {
        this.id = id;
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

