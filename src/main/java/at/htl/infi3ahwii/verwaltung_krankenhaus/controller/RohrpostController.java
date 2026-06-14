package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Rohrpostkapsel;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.RohrpostService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für Rohrpostkapsel
 */
@Controller
@RequestMapping("/rohrpost")
public class RohrpostController {

    @Autowired
    private RohrpostService rohrpostService;

    @Autowired
    private StationService stationService;

    @GetMapping
    public String showAllKapseln(Model model) {
        List<Rohrpostkapsel> kapseln = rohrpostService.findAll();
        model.addAttribute("kapseln", kapseln);
        return "rohrpost/list";
    }

    @GetMapping("/neu")
    public String showCreateForm(Model model) {
        model.addAttribute("kapsel", new Rohrpostkapsel());
        model.addAttribute("stationen", stationService.findAll());
        return "rohrpost/form";
    }

    @PostMapping("/senden")
    public String sendKapsel(@ModelAttribute Rohrpostkapsel kapsel, @RequestParam Long vonStationId, @RequestParam Long nachStationId) {
        kapsel.setVonStation(stationService.findById(vonStationId).orElse(null));
        kapsel.setNachStation(stationService.findById(nachStationId).orElse(null));
        rohrpostService.senden(kapsel);
        return "redirect:/rohrpost";
    }

    @GetMapping("/ankunft")
    public String ankunftSimulieren(Model model) {
        Rohrpostkapsel kapsel = rohrpostService.ankunftSimulieren();
        if (kapsel != null) {
            model.addAttribute("message", "Kapsel " + kapsel.getInhalt() + " ist angekommen!");
        } else {
            model.addAttribute("message", "Keine Kapsel unterwegs.");
        }
        List<Rohrpostkapsel> kapseln = rohrpostService.findAll();
        model.addAttribute("kapseln", kapseln);
        return "rohrpost/list";
    }

    @GetMapping("/loeschen/{id}")
    public String deleteKapsel(@PathVariable Long id) {
        rohrpostService.delete(id);
        return "redirect:/rohrpost";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Rohrpostkapsel> getAllKapseln() {
        return rohrpostService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Rohrpostkapsel getKapselById(@PathVariable Long id) {
        return rohrpostService.findById(id).orElse(null);
    }
}
