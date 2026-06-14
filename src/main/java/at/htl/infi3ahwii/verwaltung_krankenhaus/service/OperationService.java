package at.htl.infi3ahwii.verwaltung_krankenhaus.service;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.Operation;
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
 * Service für Operation
 */
@Service
public class OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    private Dao<Operation, Long> getDao() throws SQLException {
        return databaseHelper.getDao(Operation.class);
    }

    public List<Operation> findAll() {
        try {
            List<Operation> operations = getDao().queryForAll();
            logger.info("findAllOperation: ok - {} Operationen gefunden", operations.size());
            return operations;
        } catch (SQLException e) {
            logger.error("findAllOperation: fehler - {}", e.getMessage());
            return List.of();
        }
    }

    public Optional<Operation> findById(Long id) {
        try {
            Operation operation = getDao().queryForId(id);
            if (operation != null) {
                logger.info("findByIdOperation: ok - Operation mit ID {} gefunden", id);
                return Optional.of(operation);
            } else {
                logger.warn("findByIdOperation: nicht gefunden - Operation mit ID {} existiert nicht", id);
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("findByIdOperation: fehler - {}", e.getMessage());
            return Optional.empty();
        }
    }

    public Operation save(Operation operation) {
        try {
            if (operation.getCreatedAt() == null) {
                operation.setCreatedAt(LocalDateTime.now());
                operation.setUpdatedAt(LocalDateTime.now());
            } else {
                operation.setUpdatedAt(LocalDateTime.now());
            }
            getDao().create(operation);
            logger.info("saveOperation: ok - Operation {} gespeichert (ID: {})", operation.getBeschreibung(), operation.getId());
            return operation;
        } catch (SQLException e) {
            logger.error("saveOperation: fehler - {}", e.getMessage());
            return null;
        }
    }

    public Operation update(Long id, Operation operationDetails) {
        try {
            Operation operation = getDao().queryForId(id);
            if (operation != null) {
                operation.setPatient(operationDetails.getPatient());
                operation.setArzt(operationDetails.getArzt());
                operation.setKrankenschwester(operationDetails.getKrankenschwester());
                operation.setOpSaal(operationDetails.getOpSaal());
                operation.setBeschreibung(operationDetails.getBeschreibung());
                operation.setGeplantStart(operationDetails.getGeplantStart());
                operation.setOpStart(operationDetails.getOpStart());
                operation.setOpEnde(operationDetails.getOpEnde());
                operation.setUpdatedAt(LocalDateTime.now());
                getDao().update(operation);
                logger.info("updateOperation: ok - Operation mit ID {} aktualisiert", id);
                return operation;
            } else {
                logger.warn("updateOperation: nicht gefunden - Operation mit ID {} existiert nicht", id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("updateOperation: fehler - {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            getDao().deleteById(id);
            logger.info("deleteOperation: ok - Operation mit ID {} gelöscht", id);
        } catch (SQLException e) {
            logger.error("deleteOperation: fehler - {}", e.getMessage());
        }
    }

    /**
     * Startet eine Operation
     */
    public Operation startOperation(Long id) {
        try {
            Operation operation = getDao().queryForId(id);
            if (operation != null) {
                operation.setOpStart(LocalDateTime.now());
                operation.setUpdatedAt(LocalDateTime.now());
                getDao().update(operation);
                logger.info("startOperation: ok - Operation mit ID {} gestartet", id);
                return operation;
            } else {
                logger.warn("startOperation: nicht gefunden - Operation mit ID {} existiert nicht", id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("startOperation: fehler - {}", e.getMessage());
            return null;
        }
    }

    /**
     * Beendet eine Operation
     */
    public Operation endOperation(Long id) {
        try {
            Operation operation = getDao().queryForId(id);
            if (operation != null) {
                operation.setOpEnde(LocalDateTime.now());
                operation.setUpdatedAt(LocalDateTime.now());
                getDao().update(operation);
                logger.info("endOperation: ok - Operation mit ID {} beendet", id);
                return operation;
            } else {
                logger.warn("endOperation: nicht gefunden - Operation mit ID {} existiert nicht", id);
                return null;
            }
        } catch (SQLException e) {
            logger.error("endOperation: fehler - {}", e.getMessage());
            return null;
        }
    }

    public List<Operation> findByPatientId(Long patientId) {
        try {
            List<Operation> operations = getDao().queryBuilder().where().eq("patient_id", patientId).query();
            logger.info("findByPatientIdOperation: ok - {} Operationen für Patient {} gefunden", operations.size(), patientId);
            return operations;
        } catch (SQLException e) {
            logger.error("findByPatientIdOperation: fehler - {}", e.getMessage());
            return List.of();
        }
    }
}
