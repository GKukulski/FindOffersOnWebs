package myProgram.searchOnPage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myProgram.searchOnPage.entity.Offer;
import myProgram.searchOnPage.service.SearchOnWeb;
import myProgram.searchOnPage.service.SearchOnWebFB;
import myProgram.searchOnPage.service.SearchOnWebOLX;
import myProgram.searchOnPage.service.SendMail;

@Controller
@RequestMapping("/")

public class MainController {
	
	@Value("${fb.login}")
	String loginFB;
	
	@Value("${fb.password}")
	String passwordFB;
	
	@Value("${email.login}")
	String loginEmail;
	
	@Value("${email.password}")
	String passwordEmail;
	
	
	@GetMapping("/")
	public String testProp(Model theModel) {
		return "test";
	}
		
	@GetMapping("/testOLX")
	public String testOLX(Model theModel) {
		SearchOnWeb olx = new SearchOnWebOLX();
		List<Offer> lista = olx.readAll();
		lista.sort((Offer o1, Offer o2) -> o2.getLength()-o1.getLength());
		theModel.addAttribute("web",lista);
		return "test";
	}
	
	@GetMapping("/test")
	public String test(Model theModel) {
		SearchOnWeb olx = new SearchOnWebOLX();
		List<Offer> lista = olx.readAll();
		
		SearchOnWeb fb = new SearchOnWebFB(loginFB,passwordFB);
		lista.addAll(fb.readAll());
		
		//lista.sort((Offer o1, Offer o2) -> o2.getLength()-o1.getLength());
		theModel.addAttribute("web",lista);
		return "test";
	}
	
	@GetMapping("/testEmail")
	public String testEmail(Model theModel) {
		new SendMail(loginEmail,passwordEmail).send();
		return "test";
	}
	
	@GetMapping("/testFB")
	public String testFB(Model theModel) {
		SearchOnWeb olx = new SearchOnWebFB(loginFB,passwordFB);
		List<Offer> lista = olx.readAll();
		theModel.addAttribute("web",lista);
		return "test";
	}
	
	
	
	
}
