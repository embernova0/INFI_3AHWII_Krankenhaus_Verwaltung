package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Patient;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service für Patient
 * Enthält Geschäftslogik für Patientenverwaltung
 */
@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Patient, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Patient.class);
    }

    /**
     * Gibt alle Patienten zurück
     */
    public List<Patient> findAll() {
        try {
            List<Patient> patients = getDao().queryForAll();
            logger.info("findAllPatient: ok - {} Patienten gefunden", patients.size());
            return patients;
        } catch (SQLException e) {
            logger.error("findAllPatient: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    /**
     * Findet einen Patienten nach ID
     */
    public Optional<Patient> findById(Long id) {
        try {
            Patient patient = getDao().queryForId(id);
            if (patient != null) {
                logger.info("findByIdPatient: ok - Patient mit ID {} gefunden", id);
                return Optional.of(patient);
            } else {
                logger.warn("findByIdPatient: nicht gefunden - Patient mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdPatient: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Speichert einen Patienten
     */
    public Patient save(Patient patient) {
        try {
            if (patient.getAufnahmeZeit() == null) {
                patient.setAufnahmeZeit(LocalDateTime.now());
            }
            if (patient.getCreatedAt() == null) {
                patient.setCreatedAt(LocalDateTime.now());
                patient.setUpdatedAt(LocalDateTime.now());
            } else {
                patient.setUpdatedAt(LocalDateTime.now());
            }
            getDao().create(patient);
            logger.info("savePatient: ok - Patient {} {} gespeichert (ID: {})", 
                patient.getVorname(), patient.getNachname(), patient.getId());
            return patient;
        } catch (SQLException e) {
            logger.error("savePatient: fehler - {}", e.getMessage());
            return null;
        }
    }

    /**
     * Aktualisiert einen Patienten
     */
    public Patient update(Long id, Patient patientDetails) {
        try {
            Patient patient = getDao().queryForId(id);
            if (patient != null) {
                patient.setVorname(patientDetails.getVorname());
                patient.setNachname(patientDetails.getNachname());
                patient.setGeburtsdatum(patientDetails.getGeburtsdatum());
                patient.setSvnr(patientDetails.getSvnr());
                patient.setStation(patientDetails.getStation());
                patient.setZimmer(patientDetails.getZimmer());
                patient.setUpdatedAt(LocalDateTime.now());
                getDao().update(patient);
                logger.info("updatePatient: ok - Patient mit ID {} aktualisiert", id);
                return patient;
            } else {
                logger.warn("updatePatient: nicht gefunden - Patient mit ID {} existiert nicht", id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("updatePatient: fehler - {}", e.getMessage());
            return null;
        }
    }

    /**
     * Löscht einen Patienten
     */
    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deletePatient: ok - Patient mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deletePatient: fehler - {}", e.getMessage());
        }
    }

    /**
     * Findet alle Patienten mit einem bestimmten Nachnamen
     */
    public List<Patient> findByNachname(String nachname) {
        try {
            List<Patient> patients = getDao().queryBuilder().where().eq("nachname", nachname).query();
            logger.info("findByNachnamePatient: ok - {} Patienten mit Nachname {} gefunden", patients.size(), nachname);
            return patients;
        } catch (SQLException e) {
            logger.error("findByNachnamePatient: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    /**
     * Findet alle Patienten auf einer Station
     */
    public List<Patient> findByStationId(Long stationId) {
        try {
            List<Patient> patients = getDao().queryBuilder().where().eq("station_id", stationId).query();
            logger.info("findByStationIdPatient: ok - {} Patienten auf Station {} gefunden", patients.size(), stationId);
            return patients;
        } catch (SQLException e) {
            logger.error("findByStationIdPatient: fehler - {}", e.getMessage());
            return List.of();
        }
    }
}
