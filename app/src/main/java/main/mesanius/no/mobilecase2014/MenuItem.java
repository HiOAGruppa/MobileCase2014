package main.mesanius.no.mobilecase2014;

/**
 * Created by NegatioN on 12.10.2014.
 */
public class MenuItem {
    private String description, name;
    private int price;

    public MenuItem(){

    }

    //brukes til å generere menuitems i den totale menyen
    public MenuItem(String name, int price){
        this.name = name;
        this.price = price;
    }

    //constructor for detalj-view av menuitem
    public MenuItem(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
