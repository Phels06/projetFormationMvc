package projetFormationMvc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import projetFormation.entity.Chien;
import projetFormation.service.ChienService;

@Controller 
@RequestMapping("/chien")
public class ChienController {

	
	@Autowired
	private ChienService chienService;
	
	
	@GetMapping(value = { "", "/" })
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("chien/list");
		modelAndView.addObject("chien", chienService.rechercheAll());
		return modelAndView;
	}
	
	
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam Integer id) {
		Optional<Chien> opt = Optional.ofNullable(chienService.recherche(id));
		if (opt.isPresent()) {
			return goEdit(opt.get());
		}
		return goEdit(new Chien());
	}
	
	
	private ModelAndView goEdit(Chien chien) {
		ModelAndView modelAndView = new ModelAndView("chien/edit");
		modelAndView.addObject("c", chien);
		return modelAndView;
	}
	
	
	
	
	
}
