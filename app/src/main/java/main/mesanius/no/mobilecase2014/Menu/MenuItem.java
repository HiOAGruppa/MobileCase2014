package main.mesanius.no.mobilecase2014.Menu;

/**
 * Created by NegatioN on 12.10.2014.
 */
public class MenuItem {
    private String description, name;
    private int price, id;

    public MenuItem(){

    }

    //brukes til å generere menuitems i den totale menyen
    public MenuItem(String name, int price, int id){
        this.name = name;
        this.price = price;
        this.id = id;
    }

    //constructor for detalj-view av menuitem
    public MenuItem(String name, String description, int price, int id){
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
