package at.htl.infi3ahwii.verwaltung_krankenhaus.controller;

import at.htl.infi3ahwii.verwaltung_krankenhaus.model.SensorLog;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Temperaturalarm;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.SensorLogService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.TemperaturalarmService;
import at.htl.infi3ahwii.verwaltung_krankenhaus.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller für Sensor-Ansicht (KANN-Anwendungen)
 */
@Controller
public class SensorController {

    @Autowired
    private SensorLogService sensorLogService;

    @Autowired
    private TemperaturalarmService temperaturalarmService;

    @Autowired
    private StationService stationService;

    @GetMapping("/sensoren")
    public String sensoren(Model model) {
        List<SensorLog> logs = sensorLogService.findAll();
        List<Temperaturalarm> alarme = temperaturalarmService.findAll();
        
        model.addAttribute("logs", logs);
        model.addAttribute("alarme", alarme);
        model.addAttribute("stationen", stationService.findAll());
        
        return "sensoren/index";
    }

    @PostMapping("/sensoren/log")
    public String createLog(@RequestParam String sensorTyp, @RequestParam String meldung) {
        SensorLog log = new SensorLog(sensorTyp, meldung);
        sensorLogService.save(log);
        return "redirect:/sensoren";
    }

    @PostMapping("/sensoren/alarm")
    public String createAlarm(@RequestParam Long stationId, @RequestParam Double temperatur, @RequestParam Double grenzwert) {
        Temperaturalarm alarm = new Temperaturalarm();
        alarm.setStation(stationService.findById(stationId).orElse(null));
        alarm.setTemperatur(temperatur);
        alarm.setGrenzwert(grenzwert);
        temperaturalarmService.save(alarm);
        return "redirect:/sensoren";
    }
}
