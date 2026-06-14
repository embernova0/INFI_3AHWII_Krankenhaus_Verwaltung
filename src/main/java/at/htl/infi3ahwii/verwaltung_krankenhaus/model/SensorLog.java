package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * SensorLog Entity
 * Repräsentiert einen Log-Eintrag von Sensoren
 */
@DatabaseTable(tableName = "sensor_log")
public class SensorLog {

    @DatabaseField(generatedId = true, columnName = "log_id")
    private Long id;

    @DatabaseField(columnName = "sensor_typ")
    private String sensorTyp;

    private String meldung;

    @DatabaseField(columnName = "zeitpunkt", dataType = DataType.SERIALIZABLE)
    private LocalDateTime zeitpunkt;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public SensorLog() {}

    public SensorLog(String sensorTyp, String meldung) {
        this.sensorTyp = sensorTyp;
        this.meldung = meldung;
        this.zeitpunkt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSensorTyp() {
        return sensorTyp;
    }

    public void setSensorTyp(String sensorTyp) {
        this.sensorTyp = sensorTyp;
    }

    public String getMeldung() {
        return meldung;
    }

    public void setMeldung(String meldung) {
        this.meldung = meldung;
    }

    public LocalDateTime getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(LocalDateTime zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SensorLog{" +
                "id=" + id +
                ", sensorTyp='" + sensorTyp + '\'' +
                ", meldung='" + meldung + '\'' +
                ", zeitpunkt=" + zeitpunkt +
                '}';
    }
}
