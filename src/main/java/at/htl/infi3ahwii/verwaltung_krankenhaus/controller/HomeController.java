package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Home Controller
 * Zeigt die Startseite der Anwendung mit Statistiken
 */
@Controller
public class HomeController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private RohrpostService rohrpostService;

    @Autowired
    private StationService stationService;

    @Autowired
    private ZimmerService zimmerService;

    @Autowired
    private SensorLogService sensorLogService;

    @Autowired
    private TemperaturalarmService temperaturalarmService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Krankenhausverwaltung");
        
        // Statistiken berechnen
        long patientenCount = patientService.findAll().size();
        long operationenCount = operationService.findAll().size();
        long rohrpostCount = rohrpostService.findAll().size();
        long stationenCount = stationService.findAll().size();
        long zimmerCount = zimmerService.findAll().size();
        long sensorLogsCount = sensorLogService.findAll().size();
        long alarmeCount = temperaturalarmService.findAll().size();
        
        model.addAttribute("patientenCount", patientenCount);
        model.addAttribute("operationenCount", operationenCount);
        model.addAttribute("rohrpostCount", rohrpostCount);
        model.addAttribute("stationenCount", stationenCount);
        model.addAttribute("zimmerCount", zimmerCount);
        model.addAttribute("sensorLogsCount", sensorLogsCount);
        model.addAttribute("alarmeCount", alarmeCount);
        
        return "index";
    }
}
