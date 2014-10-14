package main.mesanius.no.mobilecase2014.Order;

public class OrderListItem {
    private String name;
    private String description;
    private double price;
    private int totalprice;
    private int quantity = 1;

    public OrderListItem(String name, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public OrderListItem(String name, double price, int quantity){
        this.name = name;
        this.description = description;
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

    public void incrementQuantity (boolean inc, int num){
        for(int i = 0; i < num; i++) {
            incrementQuantity(inc);
        }
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if(quantity < 1) quantity = 0;
        else quantity--;
    }

    public void incrementQuantity(boolean inc) {
        if(inc) quantity++;
        else if(quantity > 0) quantity--;
    }
}
