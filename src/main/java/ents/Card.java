package ents;

import dao.Identified;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card implements Identified<Integer> {


    private int id;
    private int userId;
    private Date createdAt = new Date();
    private boolean paid;
    private List<CardItem> cardItems = new ArrayList();

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


    public List<CardItem> getCardItems() {
        return cardItems;
    }

    public void setCardItems(List<CardItem> cardItems) {
        this.cardItems = cardItems;
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
