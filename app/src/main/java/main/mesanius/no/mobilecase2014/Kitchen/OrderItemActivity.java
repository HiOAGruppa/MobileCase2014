package main.mesanius.no.mobilecase2014.Kitchen;

public class OrderItemActivity {
	private String title, desc;
	private int type; // 0 = deliver, 1 = eat it resturant, 2= takeaway
	private int personId;
	private int id, tableId;

	public OrderItemActivity(){

	}

	public OrderItemActivity(int id,String tit, String de, int ty) {
		this.id = id;
		title = tit;
		desc = de;
		type = ty;
	}

	public OrderItemActivity(int id,String tit, String de, int ty, int i) {
		this.id = id;
		title = tit;
		desc = de;
		type = ty;
		personId = i;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	

}
