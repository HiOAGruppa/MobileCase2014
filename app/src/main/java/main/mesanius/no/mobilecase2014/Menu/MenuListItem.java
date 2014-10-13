package main.mesanius.no.mobilecase2014.Menu;

public class MenuListItem {

    private String title;
    private int icon;
    private double price;
    private int id; 
    private String desc;

    public MenuListItem(String title, int icon, int i, String d, double p) {
        this.title = title;
        this.icon = icon;
        id = i;
        desc = d;
        price = p;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    

}