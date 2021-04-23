package myProgram.searchOnPage.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import myProgram.searchOnPage.entity.Offer;





public class ReadURL implements Find {
	private String contents;	
	private Document doc; 
	
	

	public ReadURL() {		
		contents="https://www.olx.pl/oferty/q-narty-foki/?search%5Border%5D=created_at%3Adesc";
		try {
			doc = Jsoup.connect(contents).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public List<Offer> read() {
		List<Offer> ans = new LinkedList<Offer>();
		try {
			Document doc = Jsoup.connect(contents).get();
			Elements offers = doc.getElementsByClass("offer  ");
			
			for(Element offer : offers) {				
				//System.out.println(offer);
				
				//read header and price
				Elements priceAndHeader = offer.getElementsByTag("strong");				
			    String header = priceAndHeader.get(0).text();
			    int price = converPrice(priceAndHeader.get(1).text());
			    int length = findLength(header);
			    
			    //link
			    String links = offer.getElementsByAttribute("href").get(1).attr("href");
			    //System.out.println(links);
			    
			    ans.add(new Offer(header,price, length,links));
			    
			   
			  
			}
	
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ans;
		
	}
	private int findLength(String header) {
		int ans=0;
		Pattern pattern = Pattern.compile("\\d+(?= ?cm)");
		Matcher matcher = pattern.matcher(header);
		if (matcher.find()) {
			ans = Integer.parseInt(matcher.group(0));
		}
		if(ans==0) {
			pattern = Pattern.compile("1\\d{2}");
			matcher = pattern.matcher(header);
			if (matcher.find()) {
				ans = Integer.parseInt(matcher.group(0));
			}
		}
		return ans;
	}

	//convert Price(Strong->int): OLX Standard
	private int converPrice(String priceString) {
		int price = 0;
		Pattern pattern = Pattern.compile("\\d* \\d*");
		Matcher matcher = pattern.matcher(priceString);
		if (matcher.find()) {
			 
			 String priceCutString = matcher.group(0);
			 //System.out.println(priceCutString);
			 String[] tabStrings = priceCutString.split(" ");
			 if(tabStrings.length == 2) {
				 price= Integer.parseInt(tabStrings[0])*1000 + Integer.parseInt(tabStrings[1]);
			 }
			 else {
				 price = Integer.parseInt(tabStrings[0]);
			 }
		}
		return price;
	}
	
	
}
