package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Zimmer;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Service für Zimmer
 */
@Service
public class ZimmerService {

    private static final Logger logger = LoggerFactory.getLogger(ZimmerService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Zimmer, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Zimmer.class);
    }

    public List<Zimmer> findAll() {
        try {
            List<Zimmer> zimmer = getDao().queryForAll();
            logger.info("findAllZimmer: ok - {} Zimmer gefunden", zimmer.size());
            return zimmer;
        } catch (SQLException e) {
            logger.error("findAllZimmer: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Zimmer> findById(Long id) {
        try {
            Zimmer zimmer = getDao().queryForId(id);
            if (zimmer != null) {
                logger.info("findByIdZimmer: ok - Zimmer mit ID {} gefunden", id);
                return Optional.of(zimmer);
            } else {
                logger.warn("findByIdZimmer: nicht gefunden - Zimmer mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdZimmer: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Zimmer save(Zimmer zimmer) {
        try {
            if (zimmer.getCreatedAt() == null) {
                zimmer.setCreatedAt(java.time.LocalDateTime.now());
                zimmer.setUpdatedAt(java.time.LocalDateTime.now());
            } else {
                zimmer.setUpdatedAt(java.time.LocalDateTime.now());
            }
            getDao().create(zimmer);
            logger.info("saveZimmer: ok - Zimmer {} gespeichert (ID: {})", zimmer.getZimmernummer(), zimmer.getId());
            return zimmer;
        } catch (SQLException e) {
            logger.error("saveZimmer: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteZimmer: ok - Zimmer mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteZimmer: fehler - {}", e.getMessage());
        }
    }

    public List<Zimmer> findByStationId(Long stationId) {
        try {
            List<Zimmer> zimmer = getDao().queryBuilder().where().eq("station_id", stationId).query();
            logger.info("findByStationIdZimmer: ok - {} Zimmer auf Station {} gefunden", zimmer.size(), stationId);
            return zimmer;
        } catch (SQLException e) {
            logger.error("findByStationIdZimmer: fehler - {}", e.getMessage());
            return List.of();
        }
    }
}
