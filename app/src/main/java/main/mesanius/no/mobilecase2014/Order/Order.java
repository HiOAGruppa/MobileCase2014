package main.mesanius.no.mobilecase2014.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NegatioN on 14.10.2014.
 */
public class Order {

    private int orderId, firstItemOrderId, orderLength;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order(){

    }

    public Order(int orderId, int firstItemOrderId){
        this.orderId = orderId;
        this.firstItemOrderId = firstItemOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFirstItemOrderId() {
        return firstItemOrderId;
    }

    public void setFirstItemOrderId(int firstItemOrderId) {
        this.firstItemOrderId = firstItemOrderId;
    }

    public int getOrderLength() {
        return orderLength;
    }

    public void setOrderLength(int orderLength) {
        this.orderLength = orderLength;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public boolean addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderLength++;
        return true;
    }
}
