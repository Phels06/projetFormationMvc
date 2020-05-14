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
	
	@GetMapping("/add")
	public ModelAndView addAnnonce() {
		return goEdit(new Annonce());
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
//		if (stagiaire.getFormation() != null && stagiaire.getFormation().getId() == null) {
//			stagiaire.setFormation(null);
//		}
		return save(annonce, br);
	}

	private ModelAndView save(Annonce annonce, BindingResult br) {
		if (br.hasErrors()) {
			return goEdit(annonce);
		}
		annonceService.ajout(annonce);
		return new ModelAndView("redirect:/annonce");
	}
//
//	@GetMapping("/competence/add")
//	public ModelAndView addCompetence(@RequestParam Integer id) {
//		ModelAndView modelAndView = new ModelAndView("personne/competences");
//		modelAndView.addObject("matieres", matiereRepository.findMatieresNonAffectes(id));
//
//		Competence competence = new Competence();
//		CompetencePK competenceId = new CompetencePK();
//		competenceId.setFormateur((Formateur) personneRepository.findByIdWithCompetences(id).get());
//		competence.setId(competenceId);
//		modelAndView.addObject("competence", competence);
//		return modelAndView;
//	}
//
//	@PostMapping("/competence/add")
//	public ModelAndView saveCompetence(@ModelAttribute("competence") Competence competence) {
//		competenceRepository.save(competence);
//		return new ModelAndView("redirect:/personne/competence/add?id=" + competence.getId().getFormateur().getId());
//	}
//
//}

}
