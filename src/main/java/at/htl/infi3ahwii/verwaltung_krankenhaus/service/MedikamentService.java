package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Medikament;
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
 * Service für Medikament
 */
@Service
public class MedikamentService {

    private static final Logger logger = LoggerFactory.getLogger(MedikamentService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Medikament, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Medikament.class);
    }

    public List<Medikament> findAll() {
        try {
            List<Medikament> medikamente = getDao().queryForAll();
            logger.info("findAllMedikament: ok - {} Medikamente gefunden", medikamente.size());
            return medikamente;
        } catch (SQLException e) {
            logger.error("findAllMedikament: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Medikament> findById(Long id) {
        try {
            Medikament medikament = getDao().queryForId(id);
            if (medikament != null) {
                logger.info("findByIdMedikament: ok - Medikament mit ID {} gefunden", id);
                return Optional.of(medikament);
            } else {
                logger.warn("findByIdMedikament: nicht gefunden - Medikament mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdMedikament: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Medikament save(Medikament medikament) {
        try {
            if (medikament.getCreatedAt() == null) {
                medikament.setCreatedAt(LocalDateTime.now());
                medikament.setUpdatedAt(LocalDateTime.now());
            } else {
                medikament.setUpdatedAt(LocalDateTime.now());
            }
            getDao().create(medikament);
            logger.info("saveMedikament: ok - Medikament {} gespeichert (ID: {})", medikament.getName(), medikament.getId());
            return medikament;
        } catch (SQLException e) {
            logger.error("saveMedikament: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteMedikament: ok - Medikament mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteMedikament: fehler - {}", e.getMessage());
        }
    }
}
