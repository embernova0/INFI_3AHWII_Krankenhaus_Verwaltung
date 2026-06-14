package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Rohrpostkapsel;
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
import java.util.Random;

/**
 * Service für Rohrpostkapsel
 */
@Service
public class RohrpostService {

    private static final Logger logger = LoggerFactory.getLogger(RohrpostService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private final Random random = new Random();

    private Dao<Rohrpostkapsel, Long> getKapselDao() throws SQLException {
        return databaseHelper.getDao(Rohrpostkapsel.class);
    }

    private Dao<SensorLog, Long> getSensorLogDao() throws SQLException {
        return databaseHelper.getDao(SensorLog.class);
    }

    public List<Rohrpostkapsel> findAll() {
        try {
            List<Rohrpostkapsel> kapseln = getKapselDao().queryForAll();
            logger.info("findAllRohrpost: ok - {} Kapseln gefunden", kapseln.size());
            return kapseln;
        } catch (SQLException e) {
            logger.error("findAllRohrpost: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Rohrpostkapsel> findById(Long id) {
        try {
            Rohrpostkapsel kapsel = getKapselDao().queryForId(id);
            if (kapsel != null) {
                logger.info("findByIdRohrpost: ok - Kapsel mit ID {} gefunden", id);
                return Optional.of(kapsel);
            } else {
                logger.warn("findByIdRohrpost: nicht gefunden - Kapsel mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdRohrpost: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Rohrpostkapsel save(Rohrpostkapsel kapsel) {
        try {
            if (kapsel.getCreatedAt() == null) {
                kapsel.setCreatedAt(LocalDateTime.now());
                kapsel.setUpdatedAt(LocalDateTime.now());
            } else {
                kapsel.setUpdatedAt(LocalDateTime.now());
            }
            getKapselDao().create(kapsel);
            logger.info("saveRohrpost: ok - Kapsel {} gespeichert (ID: {})", kapsel.getInhalt(), kapsel.getId());
            return kapsel;
        } catch (SQLException e) {
            logger.error("saveRohrpost: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getKapselDao().deleteById(id);
            logger.info("deleteRohrpost: ok - Kapsel mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteRohrpost: fehler - {}", e.getMessage());
        }
    }

    public Rohrpostkapsel senden(Rohrpostkapsel kapsel) {
        try {
            kapsel.setVersendetAm(LocalDateTime.now());
            kapsel.setUpdatedAt(LocalDateTime.now());
            
            if (kapsel.getId() == null) {
                kapsel.setCreatedAt(LocalDateTime.now());
                getKapselDao().create(kapsel);
            } else {
                getKapselDao().update(kapsel);
            }
            
            SensorLog log = new SensorLog("ROHRPOST", 
                "Kapsel versendet - " + kapsel.getInhalt() + " von " + 
                kapsel.getVonStation().getName() + " nach " + kapsel.getNachStation().getName());
            getSensorLogDao().create(log);
            
            logger.info("sendenRohrpost: ok - Kapsel {} versendet (ID: {})", kapsel.getInhalt(), kapsel.getId());
            return kapsel;
        } catch (SQLException e) {
            logger.error("sendenRohrpost: fehler - {}", e.getMessage());
            return null;
        }
    }

    public Rohrpostkapsel ankunftSimulieren() {
        try {
            List<Rohrpostkapsel> unterwegs = getKapselDao().queryBuilder().where().isNull("angekommen_am").query();
            
            if (!unterwegs.isEmpty()) {
                Rohrpostkapsel kapsel = unterwegs.get(random.nextInt(unterwegs.size()));
                
                if (kapsel.getNachStation() == null) {
                    logger.warn("ankunftSimulierenRohrpost: fehler - nachStation ist null");
                    return null;
                }
                
                kapsel.setAngekommenAm(LocalDateTime.now());
                kapsel.setUpdatedAt(LocalDateTime.now());
                getKapselDao().update(kapsel);
                
                SensorLog log = new SensorLog("ROHRPOST", 
                    "Kapsel angekommen - " + kapsel.getInhalt() + " bei " + 
                    kapsel.getNachStation().getName());
                getSensorLogDao().create(log);
                
                logger.info("ankunftSimulierenRohrpost: ok - Kapsel {} angekommen (ID: {})", kapsel.getInhalt(), kapsel.getId());
                return kapsel;
            }
            logger.warn("ankunftSimulierenRohrpost: keine Kapseln unterwegs");
            return null;
        } catch (SQLException e) {
            logger.error("ankunftSimulierenRohrpost: fehler - {}", e.getMessage());
            return null;
        }
    }

    public List<Rohrpostkapsel> findByVonStationId(Long stationId) {
        try {
            List<Rohrpostkapsel> kapseln = getKapselDao().queryBuilder().where().eq("von_station", stationId).query();
            logger.info("findByVonStationIdRohrpost: ok - {} Kapseln von Station {} gefunden", kapseln.size(), stationId);
            return kapseln;
        } catch (SQLException e) {
            logger.error("findByVonStationIdRohrpost: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public List<Rohrpostkapsel> findByNachStationId(Long stationId) {
        try {
            List<Rohrpostkapsel> kapseln = getKapselDao().queryBuilder().where().eq("nach_station", stationId).query();
            logger.info("findByNachStationIdRohrpost: ok - {} Kapseln zu Station {} gefunden", kapseln.size(), stationId);
            return kapseln;
        } catch (SQLException e) {
            logger.error("findByNachStationIdRohrpost: fehler - {}", e.getMessage());
            return List.of();
        }
    }
}
