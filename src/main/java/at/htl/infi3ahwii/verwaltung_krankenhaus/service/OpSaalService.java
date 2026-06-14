package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.OpSaal;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Service für OpSaal
 */
@Service
public class OpSaalService {

    private static final Logger logger = LoggerFactory.getLogger(OpSaalService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<OpSaal, Long> getDao() throws SQLException {
        return databaseHelper.getDao(OpSaal.class);
    }

    public List<OpSaal> findAll() {
        try {
            List<OpSaal> saale = getDao().queryForAll();
            logger.info("findAllOpSaal: ok - {} OP-Säle gefunden", saale.size());
            return saale;
        } catch (SQLException e) {
            logger.error("findAllOpSaal: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<OpSaal> findById(Long id) {
        try {
            OpSaal saal = getDao().queryForId(id);
            if (saal != null) {
                logger.info("findByIdOpSaal: ok - OP-Saal mit ID {} gefunden", id);
                return Optional.of(saal);
            } else {
                logger.warn("findByIdOpSaal: nicht gefunden - OP-Saal mit ID {} nicht gefunden", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdOpSaal: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public OpSaal save(OpSaal saal) {
        try {
            if (saal.getCreatedAt() == null) {
                saal.setCreatedAt(java.time.LocalDateTime.now());
                saal.setUpdatedAt(java.time.LocalDateTime.now());
            } else {
                saal.setUpdatedAt(java.time.LocalDateTime.now());
            }
            getDao().create(saal);
            logger.info("saveOpSaal: ok - OP-Saal mit ID {} gespeichert", saal.getId());
            return saal;
        } catch (SQLException e) {
            logger.error("saveOpSaal: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteOpSaal: ok - OP-Saal mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteOpSaal: fehler - {}", e.getMessage());
        }
    }
}
