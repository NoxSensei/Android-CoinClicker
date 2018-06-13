package com.project.nox.coinclicker.play.fragments.shop;

public class Item {
    private int id;
    private String title;
    private String shortdesc;
    private long count;
    private int price;
    private int image;

    public Item(int id, String title, String shortdesc, long count, int price, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.count = count;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
