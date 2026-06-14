package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * Temperaturalarm Entity
 * Repräsentiert einen Temperaturalarm im Krankenhaus
 */
@DatabaseTable(tableName = "temperaturalarm")
public class Temperaturalarm {

    @DatabaseField(generatedId = true, columnName = "alarm_id")
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "station_id")
    private Station station;

    private double temperatur;

    private double grenzwert;

    @DatabaseField(columnName = "alarmzeit", dataType = DataType.SERIALIZABLE)
    private LocalDateTime alarmzeit;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Temperaturalarm() {}

    public Temperaturalarm(Station station, double temperatur, double grenzwert) {
        this.station = station;
        this.temperatur = temperatur;
        this.grenzwert = grenzwert;
        this.alarmzeit = LocalDateTime.now();
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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public double getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(double temperatur) {
        this.temperatur = temperatur;
    }

    public double getGrenzwert() {
        return grenzwert;
    }

    public void setGrenzwert(double grenzwert) {
        this.grenzwert = grenzwert;
    }

    public LocalDateTime getAlarmzeit() {
        return alarmzeit;
    }

    public void setAlarmzeit(LocalDateTime alarmzeit) {
        this.alarmzeit = alarmzeit;
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
        return "Temperaturalarm{" +
                "id=" + id +
                ", temperatur=" + temperatur +
                ", grenzwert=" + grenzwert +
                ", alarmzeit=" + alarmzeit +
                '}';
    }
}
