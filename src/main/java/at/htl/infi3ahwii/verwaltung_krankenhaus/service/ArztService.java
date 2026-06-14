package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Arzt;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Service für Arzt
 */
@Service
public class ArztService {

    private static final Logger logger = LoggerFactory.getLogger(ArztService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Arzt, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Arzt.class);
    }

    public List<Arzt> findAll() {
        try {
            List<Arzt> aerzte = getDao().queryForAll();
            logger.info("findAllArzt: ok - {} Ärzte gefunden", aerzte.size());
            return aerzte;
        } catch (SQLException e) {
            logger.error("findAllArzt: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Arzt> findById(Long id) {
        try {
            Arzt arzt = getDao().queryForId(id);
            if (arzt != null) {
                logger.info("findByIdArzt: ok - Arzt mit ID {} gefunden", id);
                return Optional.of(arzt);
            } else {
                logger.warn("findByIdArzt: nicht gefunden - Arzt mit ID {} nicht gefunden", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdArzt: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Arzt save(Arzt arzt) {
        try {
            if (arzt.getCreatedAt() == null) {
                arzt.setCreatedAt(java.time.LocalDateTime.now());
                arzt.setUpdatedAt(java.time.LocalDateTime.now());
            } else {
                arzt.setUpdatedAt(java.time.LocalDateTime.now());
            }
            getDao().create(arzt);
            logger.info("saveArzt: ok - Arzt mit ID {} gespeichert", arzt.getId());
            return arzt;
        } catch (SQLException e) {
            logger.error("saveArzt: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteArzt: ok - Arzt mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteArzt: fehler - {}", e.getMessage());
        }
    }
}
