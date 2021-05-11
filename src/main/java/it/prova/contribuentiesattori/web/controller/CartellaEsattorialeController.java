package it.prova.contribuentiesattori.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;
import it.prova.contribuentiesattori.model.StatoCartellaEsattoriale;
import it.prova.contribuentiesattori.service.cartellaesattoriale.CartellaEsattorialeService;
import it.prova.contribuentiesattori.service.contribuente.ContribuenteService;

@Controller
@RequestMapping(value = "/cartellaesattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private ContribuenteService contribuenteService;
	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@GetMapping
	public ModelAndView listAllCartelleEsattoriali() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartella_list_attribute", cartelle);
		mv.setViewName("cartellaesattoriale/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createCartellaEsattoriale(Model model) {
		CartellaEsattoriale ca = new CartellaEsattoriale(StatoCartellaEsattoriale.CREATA);
		model.addAttribute("insert_cartella_attr", ca);
		return "cartellaesattoriale/insert";
	}

	@PostMapping("/save")
	public String saveCartellaEsattoriale(
			@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattoriale cartellaInstance, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (cartellaInstance.getContribuente() != null && cartellaInstance.getContribuente().getId() != null) {
			cartellaInstance.setContribuente(
					contribuenteService.caricaSingoloElemento(cartellaInstance.getContribuente().getId()));
		}

		if (result.hasErrors()) {
			return "cartellaesattoriale/insert";
		}

		cartellaEsattorialeService.inserisciNuovo(cartellaInstance);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

	@GetMapping("/search")
	public String searchCartellaEsattoriale(Model model) {
		model.addAttribute("list_stati_attr", StatoCartellaEsattoriale.values());
		return "cartellaesattoriale/search";
	}

	@PostMapping("/list")
	public String listCartellaEsattoriale(ModelMap model, CartellaEsattoriale cartellaEsattorialeExample,
			HttpServletRequest request) {
		if (request.getParameter("contribuente.id") != null && !request.getParameter("contribuente.id").equals("")) {
			cartellaEsattorialeExample.setContribuente(
					contribuenteService.caricaSingoloElemento(Long.parseLong(request.getParameter("contribuente.id"))));
		}

		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.findByExample(cartellaEsattorialeExample);
		model.addAttribute("cartella_list_attribute", cartelle);
		return "cartellaesattoriale/list";
	}

	@GetMapping("/show/{idCartellaEsattoriale}")
	public String showCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
		model.addAttribute("show_cartella_attr",
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
		return "cartellaesattoriale/show";
	}

	@GetMapping("/edit/{idCartellaEsattoriale}")
	public String displayCartellaEsattorialeToEdit(@PathVariable(required = true) Long idCartellaEsattoriale,
			Model model) {
		model.addAttribute("update_cartella_attr",
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
		model.addAttribute("list_stati_attr", StatoCartellaEsattoriale.values());
		return "cartellaesattoriale/edit";
	}

	@PostMapping("/edit/modify")
	public String editCartellaEsattoriale(
			@Valid @ModelAttribute("update_film_attr") CartellaEsattoriale cartellaEsattorialeInstance,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (cartellaEsattorialeInstance.getContribuente() != null
				&& cartellaEsattorialeInstance.getContribuente().getId() != null) {
			cartellaEsattorialeInstance.setContribuente(
					contribuenteService.caricaSingoloElemento(cartellaEsattorialeInstance.getContribuente().getId()));
		}
		if (result.hasErrors()) {
			return "cartellaesattoriale/edit";
		}
		cartellaEsattorialeService.aggiorna(cartellaEsattorialeInstance);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

	@GetMapping("/disable/{idCartellaEsattoriale}")
	public String displayCartellaEsattorialeToRemove(@PathVariable(required = true) Long idCartellaEsattoriale,
			Model model) {
		model.addAttribute("disable_cartella_attr",
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
		return "cartellaesattoriale/disable";
	}

	@PostMapping("/disable/remove")
	public String removeCartellaEsattoriale(
			@ModelAttribute("disable_cartella_attr") CartellaEsattoriale cartellaEsattorialeInstance,
			BindingResult result, RedirectAttributes redirectAttrs) {
		cartellaEsattorialeService
				.disabilita(cartellaEsattorialeService.caricaSingoloElemento(cartellaEsattorialeInstance.getId()));
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

}
