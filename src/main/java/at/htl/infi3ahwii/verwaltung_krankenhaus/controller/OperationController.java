package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Operation;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.OperationService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.PatientService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.ArztService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.KrankenschwesterService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.OpSaalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für Operation
 */
@Controller
@RequestMapping("/operationen")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ArztService arztService;

    @Autowired
    private KrankenschwesterService krankenschwesterService;

    @Autowired
    private OpSaalService opSaalService;

    @GetMapping
    public String showAllOperations(Model model) {
        List<Operation> operationen = operationService.findAll();
        model.addAttribute("operationen", operationen);
        model.addAttribute("operationTypes", new String[]{"Hirnoperation", "Herzoperation", "Beinoperation", "Armoperation", "Appendektomie", "Knieoperation", "Hüftoperation", "Augenoperation"});
        return "operationen/list";
    }

    @GetMapping("/neu")
    public String showCreateForm(Model model) {
        model.addAttribute("operation", new Operation());
        model.addAttribute("patienten", patientService.findAll());
        model.addAttribute("aerzte", arztService.findAll());
        model.addAttribute("schwestern", krankenschwesterService.findAll());
        model.addAttribute("saale", opSaalService.findAll());
        model.addAttribute("operationTypes", new String[]{"Hirnoperation", "Herzoperation", "Beinoperation", "Armoperation", "Appendektomie", "Knieoperation", "Hüftoperation", "Augenoperation"});
        return "operationen/form";
    }

    @GetMapping("/bearbeiten/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return operationService.findById(id)
                .map(operation -> {
                    model.addAttribute("operation", operation);
                    model.addAttribute("patienten", patientService.findAll());
                    model.addAttribute("aerzte", arztService.findAll());
                    model.addAttribute("schwestern", krankenschwesterService.findAll());
                    model.addAttribute("saale", opSaalService.findAll());
                    model.addAttribute("operationTypes", new String[]{"Hirnoperation", "Herzoperation", "Beinoperation", "Armoperation", "Appendektomie", "Knieoperation", "Hüftoperation", "Augenoperation"});
                    return "operationen/form";
                })
                .orElse("redirect:/operationen");
    }

    @PostMapping("/speichern")
    public String saveOperation(@ModelAttribute Operation operation, @RequestParam Long patientId, @RequestParam Long arztId, @RequestParam Long schwesterId, @RequestParam Long saalId) {
        operation.setPatient(patientService.findById(patientId).orElse(null));
        operation.setArzt(arztService.findById(arztId).orElse(null));
        operation.setKrankenschwester(krankenschwesterService.findById(schwesterId).orElse(null));
        operation.setOpSaal(opSaalService.findById(saalId).orElse(null));
        operationService.save(operation);
        return "redirect:/operationen";
    }

    @GetMapping("/starten/{id}")
    public String startOperation(@PathVariable Long id) {
        operationService.startOperation(id);
        return "redirect:/operationen";
    }

    @GetMapping("/beenden/{id}")
    public String endOperation(@PathVariable Long id) {
        operationService.endOperation(id);
        return "redirect:/operationen";
    }

    @GetMapping("/loeschen/{id}")
    public String deleteOperation(@PathVariable Long id) {
        operationService.delete(id);
        return "redirect:/operationen";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Operation> getAllOperations() {
        return operationService.findAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Operation getOperationById(@PathVariable Long id) {
        return operationService.findById(id).orElse(null);
    }
}
