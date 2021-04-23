package myProgram.searchOnPage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import myProgram.searchOnPage.Service.Find;
import myProgram.searchOnPage.Service.ReadURL;

@Controller
@RequestMapping("test")
public class MainController {
	@GetMapping
	public String test(Model theModel) {
		ReadURL myReadURL = new ReadURL();
		myReadURL.read();
		theModel.addAttribute("web",myReadURL.read());
		return "test";
	}
}
