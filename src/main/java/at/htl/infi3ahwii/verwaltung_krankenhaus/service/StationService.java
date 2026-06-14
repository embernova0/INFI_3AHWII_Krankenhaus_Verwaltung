package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Station;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Service für Station
 */
@Service
public class StationService {

    private static final Logger logger = LoggerFactory.getLogger(StationService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Station, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Station.class);
    }

    public List<Station> findAll() {
        try {
            List<Station> stationen = getDao().queryForAll();
            logger.info("findAllStation: ok - {} Stationen gefunden", stationen.size());
            return stationen;
        } catch (SQLException e) {
            logger.error("findAllStation: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Station> findById(Long id) {
        try {
            Station station = getDao().queryForId(id);
            if (station != null) {
                logger.info("findByIdStation: ok - Station mit ID {} gefunden", id);
                return Optional.of(station);
            } else {
                logger.warn("findByIdStation: nicht gefunden - Station mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdStation: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Station save(Station station) {
        try {
            if (station.getCreatedAt() == null) {
                station.setCreatedAt(java.time.LocalDateTime.now());
                station.setUpdatedAt(java.time.LocalDateTime.now());
            } else {
                station.setUpdatedAt(java.time.LocalDateTime.now());
            }
            getDao().create(station);
            logger.info("saveStation: ok - Station {} gespeichert (ID: {})", station.getName(), station.getId());
            return station;
        } catch (SQLException e) {
            logger.error("saveStation: fehler - {}", e.getMessage());
            return null;
        }
    }

    public Station update(Long id, Station stationDetails) {
        try {
            Station station = getDao().queryForId(id);
            if (station != null) {
                station.setName(stationDetails.getName());
                station.setStandort(stationDetails.getStandort());
                station.setUpdatedAt(java.time.LocalDateTime.now());
                getDao().update(station);
                logger.info("updateStation: ok - Station mit ID {} aktualisiert", id);
                return station;
            } else {
                logger.warn("updateStation: nicht gefunden - Station mit ID {} existiert nicht", id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("updateStation: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteStation: ok - Station mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteStation: fehler - {}", e.getMessage());
        }
    }

    public Optional<Station> findByName(String name) {
        try {
            List<Station> stations = getDao().queryBuilder().where().eq("name", name).query();
            if (!stations.isEmpty()) {
                logger.info("findByNameStation: ok - Station mit Name {} gefunden", name);
                return Optional.of(stations.get(0));
            } else {
                logger.warn("findByNameStation: nicht gefunden - Station mit Name {} existiert nicht", name);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByNameStation: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }
}
