package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * Zimmer Entity
 * Repräsentiert ein Zimmer im Krankenhaus
 */
@DatabaseTable(tableName = "zimmer")
public class Zimmer {

    @DatabaseField(generatedId = true, columnName = "zimmer_id")
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "station_id")
    private Station station;

    @DatabaseField(columnName = "zimmernummer", canBeNull = false)
    private String zimmernummer;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Zimmer() {}

    public Zimmer(Station station, String zimmernummer) {
        this.station = station;
        this.zimmernummer = zimmernummer;
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

    public String getZimmernummer() {
        return zimmernummer;
    }

    public void setZimmernummer(String zimmernummer) {
        this.zimmernummer = zimmernummer;
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
        return "Zimmer{" +
                "id=" + id +
                ", zimmernummer='" + zimmernummer + '\'' +
                '}';
    }
}
