package projetFormationMvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import projetFormation.entity.Annonce;
import projetFormation.entity.Note;
import projetFormation.service.AnnonceService;

@Controller
@RequestMapping("/annonce")
public class AnnonceController {

	@Autowired
	private AnnonceService annonceService;

	@GetMapping(value = { "", "/" })
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("annonce/list");
		modelAndView.addObject("annonces", annonceService.rechercheTous());
		return modelAndView;
	}
	
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam Integer id) {
		Annonce annonce = annonceService.recherche(id);
		return goEdit(annonce);
	}

	private ModelAndView goEdit(Annonce annonce) {
		ModelAndView modelAndView = new ModelAndView("annonce/edit");
		modelAndView.addObject("annonce", annonce);
		modelAndView.addObject("notes", Note.values());
		//modelAndView.addObject("chiens", chienService.rechercheTousParMaitre());
		return modelAndView;
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Integer id) {
		annonceService.supprimerParId(id);
		return new ModelAndView("redirect:/annonce");
	}

	@GetMapping("/save")
	public ModelAndView saveAnnonce(@Valid @ModelAttribute("annonce") Annonce annonce, BindingResult br) {
//		PostulerKey postulerKey = new PostulerKey(maitre, annonce);
//		Competence competence = new Competence(competencePK, niveau);
//		Set<Competence> competences = formateur.getCompetences();
//		competences.add(competence);
//		formateur.setCompetences(competences);
		return save(annonce, br);
	}

	private ModelAndView save(Annonce annonce, BindingResult br) {
		if (br.hasErrors()) {
			return goEdit(annonce);
		}
		annonceService.ajout(annonce);
		return new ModelAndView("redirect:");
	}
}
