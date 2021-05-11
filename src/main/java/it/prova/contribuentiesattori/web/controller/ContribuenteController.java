package it.prova.contribuentiesattori.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;
import it.prova.contribuentiesattori.model.Contribuente;
import it.prova.contribuentiesattori.service.contribuente.ContribuenteService;

@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteServiceInstance;

	@GetMapping
	public ModelAndView listAllContribuenti() {
		ModelAndView mv = new ModelAndView();
		List<Contribuente> contribuenti = contribuenteServiceInstance.listAllElements();
		mv.addObject("contribuente_list_attribute", contribuenti);
		mv.setViewName("contribuente/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createContribuente(Model model) {
		model.addAttribute("insert_contribuente_attr", new Contribuente());
		return "contribuente/insert";
	}

	@PostMapping("/save")
	public String saveContribuente(@Valid @ModelAttribute("insert_contribuente_attr") Contribuente contribuente,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "contribuente/insert";
		}

		contribuenteServiceInstance.inserisciNuovo(contribuente);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";

	}

	@GetMapping("/search")
	public String searchContribuente() {
		return "contribuente/search";
	}

	@PostMapping("/list")
	public String listContribuenti(Contribuente contribuenteExample, ModelMap model) {
		List<Contribuente> contribuenti = contribuenteServiceInstance.findByExample(contribuenteExample);
		model.addAttribute("contribuente_list_attribute", contribuenti);
		return "contribuente/list";
	}

	@GetMapping("/show/{idContribuente}")
	public String showContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		Contribuente contribuente = contribuenteServiceInstance.caricaSingoloElementoEager(idContribuente);
		model.addAttribute("visualizza_contribuente_attr", contribuente);
        model.addAttribute("show_importo_totale", getImportoTotale(contribuente));
        model.addAttribute("show_importo_concluso", getTotConcluso(contribuente));
        model.addAttribute("show_totale_contenzioso", getTotCartelleInContenzioso(contribuente));
		return "contribuente/show";
	}

	@GetMapping(value = "/searchContribuentiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchContribuente(@RequestParam String term) {

		List<Contribuente> listaRegistaByTerm = contribuenteServiceInstance.cercaByCognomeENomeLike(term);
		return buildJsonResponse(listaRegistaByTerm);
	}

	private String buildJsonResponse(List<Contribuente> listaContribuenti) {
		JsonArray ja = new JsonArray();

		for (Contribuente registaItem : listaContribuenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", registaItem.getId());
			jo.addProperty("label", registaItem.getNome() + " " + registaItem.getCognome());
			ja.add(jo);
		}
		return new Gson().toJson(ja);
	}

	@GetMapping("/edit/{idContribuente}")
	public String displayContribuenteToEdit(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("update_contribuente_attr",
				contribuenteServiceInstance.caricaSingoloElemento(idContribuente));
		return "contribuente/edit";
	}

	@PostMapping("/edit/modify")
	public String editContribuente(@Valid @ModelAttribute("update_contribuente_attr") Contribuente contribuente,
			BindingResult result, RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "contribuente/edit";
		}
		contribuenteServiceInstance.aggiorna(contribuente);
		redirectAttr.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";

	}

	@GetMapping("/delete/{idContribuente}")
	public String displayContribuenteToRemove(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("delete_contribuente_attr",
				contribuenteServiceInstance.caricaSingoloElementoEager(idContribuente));
		return "contribuente/delete";
	}

	@PostMapping("/delete/remove")
	public String removeContribuenteEsattoriale(
			@ModelAttribute("delete_contribuente_attr") Contribuente contribuenteInstance,
			RedirectAttributes redirectAttrs) {
		contribuenteServiceInstance.rimuovi(contribuenteInstance);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
    public double getImportoTotale(Contribuente contribuenteInstance) {
        double importoTotale = 0;
        for (CartellaEsattoriale item : contribuenteInstance.getCartelleEsattoriali()) {
            if (!item.getStatoCartella().name().equalsIgnoreCase("INVALIDATA"))
                importoTotale += item.getImporto();
            continue;
        }
        return importoTotale;
    }
    
	public double getTotConcluso(Contribuente contribuenteInstance) {
        double importoTotale = 0;
        for (CartellaEsattoriale item : contribuenteInstance.getCartelleEsattoriali()) {
            if (item.getStatoCartella().name().equalsIgnoreCase("CONCLUSA"))
                importoTotale += item.getImporto();
            continue;
        }
        return importoTotale;
    }
    
    public double getTotCartelleInContenzioso(Contribuente contribuenteInstance) {
        int totContenziose = 0;
        for (CartellaEsattoriale item : contribuenteInstance.getCartelleEsattoriali()) {
            if (item.getStatoCartella().name().equalsIgnoreCase("IN_CONTENZIOSO"))
                totContenziose += item.getImporto();
            continue;
        }
        return totContenziose;
    }
	
}
