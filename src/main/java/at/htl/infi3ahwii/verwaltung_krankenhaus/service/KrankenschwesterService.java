package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Krankenschwester;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Service für Krankenschwester
 */
@Service
public class KrankenschwesterService {

    private static final Logger logger = LoggerFactory.getLogger(KrankenschwesterService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Krankenschwester, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Krankenschwester.class);
    }

    public List<Krankenschwester> findAll() {
        try {
            List<Krankenschwester> schwestern = getDao().queryForAll();
            logger.info("findAllKrankenschwester: ok - {} Krankenschwestern gefunden", schwestern.size());
            return schwestern;
        } catch (SQLException e) {
            logger.error("findAllKrankenschwester: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Krankenschwester> findById(Long id) {
        try {
            Krankenschwester schwester = getDao().queryForId(id);
            if (schwester != null) {
                logger.info("findByIdKrankenschwester: ok - Krankenschwester mit ID {} gefunden", id);
                return Optional.of(schwester);
            } else {
                logger.warn("findByIdKrankenschwester: nicht gefunden - Krankenschwester mit ID {} nicht gefunden", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdKrankenschwester: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Krankenschwester save(Krankenschwester schwester) {
        try {
            if (schwester.getCreatedAt() == null) {
                schwester.setCreatedAt(java.time.LocalDateTime.now());
                schwester.setUpdatedAt(java.time.LocalDateTime.now());
            } else {
                schwester.setUpdatedAt(java.time.LocalDateTime.now());
            }
            getDao().create(schwester);
            logger.info("saveKrankenschwester: ok - Krankenschwester mit ID {} gespeichert", schwester.getId());
            return schwester;
        } catch (SQLException e) {
            logger.error("saveKrankenschwester: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteKrankenschwester: ok - Krankenschwester mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteKrankenschwester: fehler - {}", e.getMessage());
        }
    }
}
