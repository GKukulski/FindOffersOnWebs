package myProgram.searchOnPage.entity;

public class Offer {
	private String title;
	private int price;
	private int length;
	
	
	public Offer(String title, int price, int length) {
		this.title = title;
		this.price = price;
		this.length = length;
	}
	//geters and Settter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setCena(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Offer [title=" + title + ", price=" + price + "]";
	}
	
	
	
	

}
