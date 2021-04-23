package myProgram.searchOnPage.entity;

public class Offer {
	private String title;
	private int price;
	private int length;
	private String link;
	
	
	public Offer(String title, int price, int length, String link) {
		this.title = title;
		this.price = price;
		this.length = length;
		this.link = link;
	}
	//geters and Settter
	
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
