package myProgram.searchOnPage.Controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.tomcat.jni.Mmap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import myProgram.searchOnPage.Service.Find;
import myProgram.searchOnPage.Service.ReadURL;
import myProgram.searchOnPage.entity.Offer;

@Controller
@RequestMapping("/test")
public class MainController {
	@GetMapping
	public String test(Model theModel) {
		ReadURL myReadURL = new ReadURL();
		List<Offer> lista = myReadURL.read();
		lista.sort((Offer o1, Offer o2) -> o2.getLength()-o1.getLength());
		theModel.addAttribute("web",lista);
		return "test";
	}
}
