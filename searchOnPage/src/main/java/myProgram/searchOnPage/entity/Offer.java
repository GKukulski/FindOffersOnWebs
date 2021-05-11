package myProgram.searchOnPage.entity;

import java.util.Date;

public class Offer {
	private String title;
	private int price;
	private int length;
	private String link;
	private String place;
	private Date date;
	private String source;
	
	
	public Offer(String title, int price, int length, String link) {
		this.title = title;
		this.price = price;
		this.length = length;
		this.link = link;
	}
	
	
	public Offer(String title, int price, int length, String link, String place, Date date, String source) {
		this.title = title;
		this.price = price;
		this.length = length;
		this.link = link;
		this.place = place;
		this.date = date;
		this.source = source;
	}


	//geters and Settter
	
	public String getTitle() {
		return title;
	}
	public String getPlace() {
		return place;
	}
	
	

	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
