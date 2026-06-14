package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.SensorLog;
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
 * Service für SensorLog
 */
@Service
public class SensorLogService {

    private static final Logger logger = LoggerFactory.getLogger(SensorLogService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<SensorLog, Long> getDao() throws SQLException {
        return databaseHelper.getDao(SensorLog.class);
    }

    public List<SensorLog> findAll() {
        try {
            List<SensorLog> logs = getDao().queryForAll();
            logger.info("findAllSensorLog: ok - {} SensorLogs gefunden", logs.size());
            return logs;
        } catch (SQLException e) {
            logger.error("findAllSensorLog: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<SensorLog> findById(Long id) {
        try {
            SensorLog log = getDao().queryForId(id);
            if (log != null) {
                logger.info("findByIdSensorLog: ok - SensorLog mit ID {} gefunden", id);
                return Optional.of(log);
            } else {
                logger.warn("findByIdSensorLog: nicht gefunden - SensorLog mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdSensorLog: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public SensorLog save(SensorLog log) {
        try {
            if (log.getCreatedAt() == null) {
                log.setCreatedAt(LocalDateTime.now());
                log.setUpdatedAt(LocalDateTime.now());
            } else {
                log.setUpdatedAt(LocalDateTime.now());
            }
            getDao().create(log);
            logger.info("saveSensorLog: ok - SensorLog gespeichert (ID: {})", log.getId());
            return log;
        } catch (SQLException e) {
            logger.error("saveSensorLog: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteSensorLog: ok - SensorLog mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteSensorLog: fehler - {}", e.getMessage());
        }
    }
}
