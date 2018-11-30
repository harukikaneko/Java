package shopping;

import java.io.Serializable;

/**
 * HistoryBean
 */
public class HistoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String item_id_; // 商品ID
	private String item_name_; // 商品名
	private int quantity_; // 購入した数
	private String select;
	private int purchased_total;
	private int day_count;
	private String orderstatus;
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderStatus() {
		return orderstatus;
	}

	public void setOrderStatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getSellBuy() {
		return sellbuy;
	}

	public void setSellBuy(String sellbuy) {
		this.sellbuy = sellbuy;
	}

	private String sellbuy;

	public int getDay_count() {
		return day_count;
	}

	public void setDay_count(int day_count) {
		this.day_count = day_count;
	}

	private int order_unit;
	{

	}

	public int getPurchased_total() {
		return purchased_total;
	}

	public void setPurchased_total(int purchased_total) {
		this.purchased_total = purchased_total;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getDateinfo() {
		return dateinfo;
	}

	public void setDateinfo(String dateinfo) {
		this.dateinfo = dateinfo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String choice;
	private String dateinfo;
	private String date;

	public HistoryBean() {
		this.item_id_ = "";
		this.item_name_ = "";
		this.quantity_ = 0;
		this.select = "";
		this.choice = "";
		this.dateinfo = "";
		this.date = "";
		this.purchased_total = 0;
		this.order_unit = 0;
		this.day_count = 0;
		this.orderstatus = "";
		this.sellbuy = "";
		this.category = "";
	}

	public HistoryBean(String item_id, String item_name, int quantity, String select, String choice, String dateinfo,
			String date, int purchased_total, int order_unit, int day_count, String orderstatus, String sellbuy,
			String category) {
		this.item_id_ = item_id;
		this.item_name_ = item_name;
		this.quantity_ = quantity;
		this.select = select;
		this.choice = choice;
		this.dateinfo = dateinfo;
		this.date = date;
		this.purchased_total = purchased_total;
		this.order_unit = order_unit;
		this.day_count = day_count;
		this.orderstatus = orderstatus;
		this.sellbuy = sellbuy;
		this.category = category;
	}

	public void setItemId(String item_id) {
		this.item_id_ = item_id;
	}

	public String getItemId() {
		return this.item_id_;
	}

	public void setItemName(String item_name) {
		this.item_name_ = item_name;
	}

	public String getItemName() {
		return this.item_name_;
	}

	public void setQuantity(int quantity) {
		this.quantity_ = quantity;
	}

	public int getQuantity() {
		return this.quantity_;
	}

	public void setOrder_unit(int order_unit) {
		this.order_unit = order_unit;
	}

	public int getOrder_unit(int order_unit) {
		return this.order_unit;

	}

	public int getOrder_unit() {
		// TODO 自動生成されたメソッド・スタブ
		return this.order_unit;
	}

}
