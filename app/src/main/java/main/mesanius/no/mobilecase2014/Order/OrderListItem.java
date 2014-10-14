package main.mesanius.no.mobilecase2014.Order;

public class OrderListItem {
    private String name;
    private String description;
    private double price;
    private int totalprice;
    private int quantity = 1;
    private int id = -1;

    public OrderListItem(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public OrderListItem(int id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(boolean increment) {
        if (increment)
            this.quantity++;
        else
            this.quantity--;
    }

    public int getId() {
        return id;
    }
}
