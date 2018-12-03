package shopping;

import java.io.Serializable;

public class FavBean implements Serializable{
	
	private String item_name;

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public  FavBean() {
		this.item_name = "";
		
	}
	

	public FavBean(String item_name) {
		this.item_name = item_name;

}
}