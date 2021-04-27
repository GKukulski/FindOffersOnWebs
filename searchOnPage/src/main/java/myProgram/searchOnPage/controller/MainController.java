package myProgram.searchOnPage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/")

public class MainController {
	
	
		
	@GetMapping("/testOLX")
	public String testOLX(Model theModel) {
		SearchOnWeb olx = new SearchOnWebOLX();
		List<Offer> lista = olx.readAll();
		lista.sort((Offer o1, Offer o2) -> o2.getLength()-o1.getLength());
		theModel.addAttribute("web",lista);
		return "test";
	}
	
	
	
	
}
