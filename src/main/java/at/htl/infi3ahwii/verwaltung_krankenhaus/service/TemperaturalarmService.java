package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Temperaturalarm;
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
 * Service für Temperaturalarm
 */
@Service
public class TemperaturalarmService {

    private static final Logger logger = LoggerFactory.getLogger(TemperaturalarmService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Temperaturalarm, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Temperaturalarm.class);
    }

    public List<Temperaturalarm> findAll() {
        try {
            List<Temperaturalarm> alarme = getDao().queryForAll();
            logger.info("findAllTemperaturalarm: ok - {} Temperaturalarme gefunden", alarme.size());
            return alarme;
        } catch (SQLException e) {
            logger.error("findAllTemperaturalarm: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Temperaturalarm> findById(Long id) {
        try {
            Temperaturalarm alarm = getDao().queryForId(id);
            if (alarm != null) {
                logger.info("findByIdTemperaturalarm: ok - Temperaturalarm mit ID {} gefunden", id);
                return Optional.of(alarm);
            } else {
                logger.warn("findByIdTemperaturalarm: nicht gefunden - Temperaturalarm mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdTemperaturalarm: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Temperaturalarm save(Temperaturalarm alarm) {
        try {
            if (alarm.getCreatedAt() == null) {
                alarm.setCreatedAt(LocalDateTime.now());
                alarm.setUpdatedAt(LocalDateTime.now());
            } else {
                alarm.setUpdatedAt(LocalDateTime.now());
            }
            getDao().create(alarm);
            logger.info("saveTemperaturalarm: ok - Temperaturalarm gespeichert (ID: {})", alarm.getId());
            return alarm;
        } catch (SQLException e) {
            logger.error("saveTemperaturalarm: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteTemperaturalarm: ok - Temperaturalarm mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteTemperaturalarm: fehler - {}", e.getMessage());
        }
    }
}
