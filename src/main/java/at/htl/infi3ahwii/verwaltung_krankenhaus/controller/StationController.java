package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Station;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller für Station
 */
@Controller
@RequestMapping("/stationen")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public String showAllStations(Model model) {
        List<Station> stationen = stationService.findAll();
        model.addAttribute("stationen", stationen);
        return "stationen/list";
    }

    @GetMapping("/neu")
    public String showCreateForm(Model model) {
        model.addAttribute("station", new Station());
        return "stationen/form";
    }

    @PostMapping("/speichern")
    public String saveStation(@ModelAttribute Station station, RedirectAttributes redirectAttributes) {
        try {
            stationService.save(station);
            redirectAttributes.addFlashAttribute("success", "Station erfolgreich erstellt!");
            return "redirect:/stationen";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Station mit diesem Namen existiert bereits!");
            return "redirect:/stationen/neu";
        }
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Station> getAllStations() {
        return stationService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Station getStationById(@PathVariable Long id) {
        return stationService.findById(id).orElse(null);
    }
}
