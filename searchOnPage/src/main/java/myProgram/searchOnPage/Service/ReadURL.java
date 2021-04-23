package myProgram.searchOnPage.Service;

import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReadURL implements Find {
	private String contents;	
	
	

	public ReadURL() {
		contents="https://www.olx.pl/oferty/q-narty-foki/?search%5Border%5D=created_at%3Adesc";
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String read() {
		Document doc;
		String ans = "";
		try {
			doc = Jsoup.connect(contents).get();
			Elements offers = doc.getElementsByClass("offer  ");
			StringBuilder sb = new StringBuilder();
			for(Element offer : offers) {
				//System.out.println("####################################################################");
				//System.out.println(offer.toString());
				//System.out.println("---------------------");
				Elements tempElements = offer.getElementsByTag("h3");
				for(Element e2: tempElements) {
					System.out.println(e2.getElementsByTag("strong").text());
				}
				//System.out.println(offer.getElementsBY("strong"));
				//System.out.println("---------------------");
			}
			//System.out.println(doc);
			ans = sb.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ans;
		
	}
	
	
}
