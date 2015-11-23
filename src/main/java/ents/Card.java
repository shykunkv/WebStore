package ents;

import dao.Identified;

import java.util.Date;

public class Card implements Identified<Integer> {


    private int id;
    private int userId;
    private Date createdAt = new Date();
    private boolean paid;


    public Card() {
    }

    public Card( int userId, Date createdAt, boolean paid) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.paid = paid;
    }

    public Card(int id, int userId, Date createdAt, boolean paid) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.paid = paid;
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
