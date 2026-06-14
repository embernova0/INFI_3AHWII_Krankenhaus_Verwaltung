package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Medikamentengabe;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.MedikamentService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.MedikamentengabeService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.PatientService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.KrankenschwesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller für Medikamentenvergabe
 */
@Controller
public class MedikamentController {

    @Autowired
    private MedikamentService medikamentService;

    @Autowired
    private MedikamentengabeService medikamentengabeService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private KrankenschwesterService krankenschwesterService;

    @GetMapping("/medikamente")
    public String medikamente(Model model) {
        List<Medikamentengabe> gaben = medikamentengabeService.findAll();
        model.addAttribute("gaben", gaben);
        return "medikamente/liste";
    }

    @GetMapping("/medikamente/neu")
    public String neuesFormular(Model model) {
        model.addAttribute("gabe", new Medikamentengabe());
        model.addAttribute("patienten", patientService.findAll());
        model.addAttribute("medikamente", medikamentService.findAll());
        model.addAttribute("krankenschwestern", krankenschwesterService.findAll());
        return "medikamente/form";
    }

    @PostMapping("/medikamente/speichern")
    public String speichern(@ModelAttribute Medikamentengabe gabe, @RequestParam Long patientId, @RequestParam Long medikamentId, @RequestParam Long krankenschwesterId) {
        gabe.setPatient(patientService.findById(patientId).orElse(null));
        gabe.setMedikament(medikamentService.findById(medikamentId).orElse(null));
        gabe.setKrankenschwester(krankenschwesterService.findById(krankenschwesterId).orElse(null));
        gabe.setVerabreichungszeit(LocalDateTime.now());
        medikamentengabeService.save(gabe);
        return "redirect:/medikamente";
    }

    @GetMapping("/medikamente/loeschen/{id}")
    public String loeschen(@PathVariable Long id) {
        medikamentengabeService.delete(id);
        return "redirect:/medikamente";
    }
}
