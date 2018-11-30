package shopping;

import java.io.Serializable;

public class ItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String item_id_;
	private String item_name_;
	private int price_;
	private int quantity_;
	private String item_code_;
	private int kabuka;
	private int text;
	private String kanriSeiri;
		
	

	public String getKanriSeiri() {
		return kanriSeiri;
	}

	public void setKanriSeiri(String kanriSeiri) {
		this.kanriSeiri = kanriSeiri;
	}

	public int getText() {
		return text;
	}

	public void setText(int text) {
		this.text = text;
	}

	public int getQuantity_() {
		return quantity_;
	}

	public void setQuantity_(int quantity_) {
		this.quantity_ = quantity_;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getAsk() {
		return ask;
	}

	public void setAsk(int ask) {
		this.ask = ask;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getYearly_high() {
		return yearly_high;
	}

	public void setYearly_high(int yearly_high) {
		this.yearly_high = yearly_high;
	}

	public int getYearly_low() {
		return yearly_low;
	}

	public void setYearly_low(int yearly_low) {
		this.yearly_low = yearly_low;
	}

	private String market;
	private int open;
	private int low;
	private int high;
	private int ask;
	private int bid;
	private int yearly_high;
	private int yearly_low;

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	private String industry;

	public int getKabuka() {
		return kabuka;
	}

	public void setKabuka(int kabuka) {
		this.kabuka = kabuka;
	}

	public int getUrikai() {
		return urikai;
	}

	public void setUrikai(int urikai) {
		this.urikai = urikai;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	private int urikai;
	private int totalPrice;


	public String getItemCode() {
		return item_code_;
	}

	public void setItemCode(String item_code) {
		this.item_code_ = item_code;
	}

	public ItemBean() {
		this.item_id_ = "";
		this.item_name_ = "";
		this.price_ = 0;
		this.quantity_ = 0;
		this.item_code_ = "";
		this.open = 0;
		this.high = 0;
		this.totalPrice = 0;
		this.market = "";
		this.industry = "";
		this.yearly_high = 0;
		this.yearly_low = 0;
		this.open = 0;
		this.low = 0;
		this.bid = 0;
		this.ask = 0;
		this.text = 0;
		this.kanriSeiri = "";

	}

	public ItemBean(String item_id, String item_name, int price, int quantity, String item_code, int totalPrice,
			String market, String industry, int open, int high, int ask, int bid, int yearly_high, int yearly_low,
			int low, int text, String kanriSeiri) {
		this.item_id_ = item_id;
		this.item_name_ = item_name;
		this.price_ = price;
		this.quantity_ = quantity;
		this.item_code_ = item_code;
		this.open = open;
		this.high = high;
		this.totalPrice = totalPrice;
		this.market = market;
		this.industry = industry;
		this.ask = ask;
		this.bid = bid;
		this.low = low;
		this.yearly_high = yearly_high;
		this.yearly_low = yearly_low;
		this.text = text;
		this.kanriSeiri = kanriSeiri;

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

	public void setPrice(int price) {
		this.price_ = price;
	}

	public int getPrice() {
		return this.price_;
	}

	public void setQuantity(int quantity) {
		this.quantity_ = quantity;
	}

	public int getQuantity() {
		return this.quantity_;
	}

}
