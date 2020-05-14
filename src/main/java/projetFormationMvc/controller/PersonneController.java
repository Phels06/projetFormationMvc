//package formationSpringMvc.controller;
//
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import formationJpaSpring.entity.Civilite;
//import formationJpaSpring.entity.Competence;
//import formationJpaSpring.entity.CompetencePK;
//import formationJpaSpring.entity.Formateur;
//import formationJpaSpring.entity.Personne;
//import formationJpaSpring.entity.Stagiaire;
//import formationJpaSpring.repository.CompetenceRepository;
//import formationJpaSpring.repository.FormationRepository;
//import formationJpaSpring.repository.MatiereRepository;
//import formationJpaSpring.repository.PersonneRepository;
//
//@Controller
//@RequestMapping("/personne")
//public class PersonneController {
//
//	@Autowired
//	private PersonneRepository personneRepository;
//	@Autowired
//	private FormationRepository formationRepository;
//	@Autowired
//	private MatiereRepository matiereRepository;
//	@Autowired
//	private CompetenceRepository competenceRepository;
//
//	@GetMapping(value = { "", "/" })
//	public ModelAndView list() {
//		// return new ModelAndView("personne/list", "personnes",
//		// personneRepository.findAll());
//		ModelAndView modelAndView = new ModelAndView("personne/list");
//		modelAndView.addObject("personnes", personneRepository.findAll());
//		return modelAndView;
//	}
//
//	@GetMapping("/formateur")
//	public ModelAndView addFormateur() {
//		return goEdit(new Formateur());
//	}
//
//	@GetMapping("/stagiaire")
//	public ModelAndView addStagiaire() {
//		return goEdit(new Stagiaire());
//	}
//
//	@GetMapping("/edit")
//	public ModelAndView edit(@RequestParam Integer id, @RequestParam String type) {
//		Optional<Personne> opt = null;
//		if (type.equals("Formateur")) {
//			opt = personneRepository.findByIdWithCompetences(id);
//		} else {
//			opt = personneRepository.findById(id);
//		}
//		if (opt.isPresent()) {
//			return goEdit(opt.get());
//		}
//		return goEdit(new Stagiaire());
//	}
//
//	private ModelAndView goEdit(Personne personne) {
//		ModelAndView modelAndView = new ModelAndView("personne/edit");
//		modelAndView.addObject("p", personne);
//		modelAndView.addObject("civilites", Civilite.values());
//		modelAndView.addObject("formations", formationRepository.findAll());
//		return modelAndView;
//	}
//
//	@GetMapping("/delete")
//	public ModelAndView delete(@RequestParam Integer id) {
//		personneRepository.deleteById(id);
//		return new ModelAndView("redirect:/personne");
//	}
//
//	@PostMapping("/save/stagiaire")
//	public ModelAndView saveStagiaire(@Valid @ModelAttribute("p") Stagiaire stagiaire, BindingResult br) {
//		if (stagiaire.getFormation() != null && stagiaire.getFormation().getId() == null) {
//			stagiaire.setFormation(null);
//		}
//		return save(stagiaire, br);
//	}
//
//	@PostMapping("/save/formateur")
//	public ModelAndView saveFormateur(@Valid @ModelAttribute("p") Formateur formateur, BindingResult br) {
//		return save(formateur, br);
//	}
//
//	private ModelAndView save(Personne personne, BindingResult br) {
//		if (br.hasErrors()) {
//			return goEdit(personne);
//		}
//		personneRepository.save(personne);
//		return new ModelAndView("redirect:/personne");
//	}
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
