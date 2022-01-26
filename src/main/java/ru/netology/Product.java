package ru.netology;

public class Product {
    private int id;
    private String name;
    private int price;



    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
