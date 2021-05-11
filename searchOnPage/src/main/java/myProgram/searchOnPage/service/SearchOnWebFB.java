package myProgram.searchOnPage.service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import myProgram.searchOnPage.entity.Offer;

public class SearchOnWebFB implements SearchOnWeb {

	private String text;
	private String login;
	private String password;
	private List<Offer> list = new ArrayList<Offer>();

	public SearchOnWebFB(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public void writeToFile() {

		try {
			System.out.println(login);
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\grzeg\\OneDrive\\Desktop\\Projekty\\FindOffersOnWebs\\searchOnPage\\src\\chromedriver.exe");

			// Switch off notifications
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);

			WebDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();

			// Accepts cookies
			driver.get("https://www.facebook.com");
			WebElement guzik = driver
					.findElement(By.xpath("//*[@id=\"facebook\"]/body/div[3]/div[2]/div/div/div/div/div[3]/button[2]"));
			System.out.println(guzik.toString());
			guzik.click();
			Thread.sleep(500);

			// Log in
			WebElement username = driver.findElement(By.id("email"));
			WebElement passwordweb = driver.findElement(By.id("pass"));
			WebElement Login = driver
					.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/div/div[1]/form/div[2]/button"));
			username.sendKeys(login);
			passwordweb.sendKeys(password);
			Login.click();
			Thread.sleep(2000);

			// ski tour fb-group
			driver.get(
					"https://www.facebook.com/groups/gieldasprzetuskiturowego?sorting_setting=RECENT_LISTING_ACTIVITY");

			// scroll down
			Thread.sleep(800);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			Thread.sleep(800);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(800);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(800);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(800);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			Thread.sleep(1800);
			// wrtie to file / String;
			FileWriter fWriter = new FileWriter("cos.txt");
			text = driver.getPageSource();
			fWriter.write(driver.getPageSource());
			fWriter.close();

			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findAd() {
		// String patternAddString =
		// "<strong><span>(.*?)</span></strong>.*?(\\d+)&nbsp";
		String patternAddString = "<strong><span>(.*?)</span></strong>.*?(\\d+)&nbsp(.*?)-webkit-box;\\\"><div>(.*?)</div>";
		Pattern patternAd = Pattern.compile(patternAddString);
		Matcher matcherAd = patternAd.matcher(text);
		while (matcherAd.find()) {
			String ad = matcherAd.group(0);

			System.out.println("XXXXXXXXXXXXXXXXXXXX");

			String seller = matcherAd.group(1);
			String price = matcherAd.group(2);
			String dateDate = findDate(ad);
			Date date = new Date();
			String opis = findDescription(ad);
			String header = findMainDesc(ad);
			String place = findPlace(ad);
			System.out.println(seller);
			System.out.println(opis);
			System.out.println(date);
			System.out.println(header);
			System.out.println(price + "zł");
			System.out.println();
			list.add(new Offer(header, Integer.parseInt(price), 0, "https://www.facebook.com/groups/gieldasprzetuskiturowego?sorting_setting=RECENT_LISTING_ACTIVITY", place, date, "FB"));
		}
	}

	public String findDate(String ad) {
		String patternString = "role=\"link\" tabindex=\"0\"><span>(.*?)</span>";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(ad);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}

	public String findDescription(String ad) {
		String patternString = "<div dir=\"auto\" style=\"text-align: start;\">(.*?)</div>";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(ad);
		String opis = "";
		while (matcher.find()) {
			opis += matcher.group(1);
		}
		return opis;
	}
	
	public String findPlace(String ad) {
		String patternString = "(\\d+)&nbsp(.*?)</span><span aria-hidden=\"true\"> · </span></span>(.*?)</div>";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(ad);
		if (matcher.find()) {
			return matcher.group(3);
		}
		return "";
	}

	public String findMainDesc(String ad) {

		String patternString = "-webkit-box;\"><div>(.*?)</div>";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(ad);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}

	@Override
	public List<Offer> readAll() {
		writeToFile();
		findAd();
		return list;
	}

}
