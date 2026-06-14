package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Patient;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.PatientService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.StationService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.ZimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für Patient
 */
@Controller
@RequestMapping("/patienten")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private StationService stationService;

    @Autowired
    private ZimmerService zimmerService;

    @GetMapping
    public String showAllPatients(Model model) {
        List<Patient> patienten = patientService.findAll();
        model.addAttribute("patienten", patienten);
        return "patienten/list";
    }

    @GetMapping("/neu")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("stationen", stationService.findAll());
        model.addAttribute("zimmer", zimmerService.findAll());
        return "patienten/form";
    }

    @GetMapping("/bearbeiten/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return patientService.findById(id)
                .map(patient -> {
                    model.addAttribute("patient", patient);
                    model.addAttribute("stationen", stationService.findAll());
                    model.addAttribute("zimmer", zimmerService.findAll());
                    return "patienten/form";
                })
                .orElse("redirect:/patienten");
    }

    @PostMapping("/speichern")
    public String savePatient(@ModelAttribute Patient patient, @RequestParam(required = false) Long stationId, @RequestParam(required = false) Long zimmerId) {
        if (stationId != null) {
            patient.setStation(stationService.findById(stationId).orElse(null));
        }
        if (zimmerId != null) {
            patient.setZimmer(zimmerService.findById(zimmerId).orElse(null));
        }
        patientService.save(patient);
        return "redirect:/patienten";
    }

    @GetMapping("/loeschen/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/patienten";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.findById(id).orElse(null);
    }
}
