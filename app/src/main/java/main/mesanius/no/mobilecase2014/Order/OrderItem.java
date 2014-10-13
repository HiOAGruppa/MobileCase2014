package main.mesanius.no.mobilecase2014.Order;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class OrderItem {

    private int itemId, quantity;

    public OrderItem(int itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
