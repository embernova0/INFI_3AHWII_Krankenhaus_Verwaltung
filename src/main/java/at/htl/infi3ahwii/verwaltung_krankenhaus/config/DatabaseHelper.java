package at.htl.infi3ahwii.verwaltung_krankenhaus.config;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * DatabaseHelper für ORMLite
 * Verwaltet die Verbindung zur SQLite-Datenbank und stellt DAOs zur Verfügung
 */
@Component
public class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.driver}")
    private String databaseDriver;

    private ConnectionSource connectionSource;

    private Map<Class<?>, Dao<?, ?>> daoMap = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            Class.forName(databaseDriver);
            
            // Verzeichnis erstellen, falls es nicht existiert
            File dbFile = new File(databaseUrl.replace("jdbc:sqlite:", ""));
            File dbDir = dbFile.getParentFile();
            if (dbDir != null && !dbDir.exists()) {
                dbDir.mkdirs();
                logger.info("DatabaseHelper: ok - Verzeichnis erstellt: {}", dbDir.getAbsolutePath());
            }
            
            connectionSource = new JdbcConnectionSource(databaseUrl);
            
            // Tabellen erstellen
            createTables();
            
            // DAOs initialisieren
            initializeDaos();
            
            logger.info("DatabaseHelper: ok - Datenbankverbindung hergestellt");
        } catch (ClassNotFoundException e) {
            logger.error("DatabaseHelper: fehler - JDBC Treiber nicht gefunden: {}", e.getMessage());
            throw new RuntimeException("JDBC Treiber nicht gefunden", e);
        } catch (SQLException e) {
            logger.error("DatabaseHelper: fehler - Datenbankverbindung fehlgeschlagen: {}", e.getMessage());
            throw new RuntimeException("Datenbankverbindung konnte nicht hergestellt werden", e);
        } catch (Exception e) {
            logger.error("DatabaseHelper: fehler - {}", e.getMessage());
            throw new RuntimeException("Datenbankverbindung konnte nicht hergestellt werden", e);
        }
    }

    @PreDestroy
    public void destroy() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
                logger.info("DatabaseHelper: ok - Datenbankverbindung geschlossen");
            } catch (Exception e) {
                logger.error("DatabaseHelper: fehler beim Schließen - {}", e.getMessage());
            }
        }
    }

    private void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Station.class);
        TableUtils.createTableIfNotExists(connectionSource, Zimmer.class);
        TableUtils.createTableIfNotExists(connectionSource, Patient.class);
        TableUtils.createTableIfNotExists(connectionSource, Arzt.class);
        TableUtils.createTableIfNotExists(connectionSource, Krankenschwester.class);
        TableUtils.createTableIfNotExists(connectionSource, OpSaal.class);
        TableUtils.createTableIfNotExists(connectionSource, Operation.class);
        TableUtils.createTableIfNotExists(connectionSource, Medikament.class);
        TableUtils.createTableIfNotExists(connectionSource, Medikamentengabe.class);
        TableUtils.createTableIfNotExists(connectionSource, Rohrpostkapsel.class);
        TableUtils.createTableIfNotExists(connectionSource, SensorLog.class);
        TableUtils.createTableIfNotExists(connectionSource, Temperaturalarm.class);
        
        logger.info("DatabaseHelper: ok - Tabellen erstellt");
    }

    private void initializeDaos() throws SQLException {
        daoMap.put(Station.class, DaoManager.createDao(connectionSource, Station.class));
        daoMap.put(Zimmer.class, DaoManager.createDao(connectionSource, Zimmer.class));
        daoMap.put(Patient.class, DaoManager.createDao(connectionSource, Patient.class));
        daoMap.put(Arzt.class, DaoManager.createDao(connectionSource, Arzt.class));
        daoMap.put(Krankenschwester.class, DaoManager.createDao(connectionSource, Krankenschwester.class));
        daoMap.put(OpSaal.class, DaoManager.createDao(connectionSource, OpSaal.class));
        daoMap.put(Operation.class, DaoManager.createDao(connectionSource, Operation.class));
        daoMap.put(Medikament.class, DaoManager.createDao(connectionSource, Medikament.class));
        daoMap.put(Medikamentengabe.class, DaoManager.createDao(connectionSource, Medikamentengabe.class));
        daoMap.put(Rohrpostkapsel.class, DaoManager.createDao(connectionSource, Rohrpostkapsel.class));
        daoMap.put(SensorLog.class, DaoManager.createDao(connectionSource, SensorLog.class));
        daoMap.put(Temperaturalarm.class, DaoManager.createDao(connectionSource, Temperaturalarm.class));
        
        logger.info("DatabaseHelper: ok - DAOs initialisiert");
    }

    @SuppressWarnings("unchecked")
    public <T, ID> Dao<T, ID> getDao(Class<T> clazz) {
        return (Dao<T, ID>) daoMap.get(clazz);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
