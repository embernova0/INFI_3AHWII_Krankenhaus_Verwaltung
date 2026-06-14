package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Medikamentengabe;
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
 * Service für Medikamentengabe
 */
@Service
public class MedikamentengabeService {

    private static final Logger logger = LoggerFactory.getLogger(MedikamentengabeService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Medikamentengabe, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Medikamentengabe.class);
    }

    public List<Medikamentengabe> findAll() {
        try {
            List<Medikamentengabe> gaben = getDao().queryForAll();
            logger.info("findAllMedikamentengabe: ok - {} Medikamentengaben gefunden", gaben.size());
            return gaben;
        } catch (SQLException e) {
            logger.error("findAllMedikamentengabe: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Medikamentengabe> findById(Long id) {
        try {
            Medikamentengabe gabe = getDao().queryForId(id);
            if (gabe != null) {
                logger.info("findByIdMedikamentengabe: ok - Medikamentengabe mit ID {} gefunden", id);
                return Optional.of(gabe);
            } else {
                logger.warn("findByIdMedikamentengabe: nicht gefunden - Medikamentengabe mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdMedikamentengabe: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Medikamentengabe save(Medikamentengabe gabe) {
        try {
            if (gabe.getCreatedAt() == null) {
                gabe.setCreatedAt(LocalDateTime.now());
                gabe.setUpdatedAt(LocalDateTime.now());
            } else {
                gabe.setUpdatedAt(LocalDateTime.now());
            }
            getDao().create(gabe);
            logger.info("saveMedikamentengabe: ok - Medikamentengabe gespeichert (ID: {})", gabe.getId());
            return gabe;
        } catch (SQLException e) {
            logger.error("saveMedikamentengabe: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteMedikamentengabe: ok - Medikamentengabe mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteMedikamentengabe: fehler - {}", e.getMessage());
        }
    }
}
