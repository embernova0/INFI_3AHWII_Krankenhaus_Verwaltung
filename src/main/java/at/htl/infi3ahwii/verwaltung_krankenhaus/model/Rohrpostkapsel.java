package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * Rohrpostkapsel Entity
 * Repräsentiert eine Kapsel im Rohrpostsystem
 */
@DatabaseTable(tableName = "rohrpostkapsel")
public class Rohrpostkapsel {

    @DatabaseField(generatedId = true, columnName = "kapsel_id")
    private Long id;

    private String inhalt;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "von_station")
    private Station vonStation;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "nach_station")
    private Station nachStation;

    @DatabaseField(columnName = "versendet_am", dataType = DataType.SERIALIZABLE)
    private LocalDateTime versendetAm;

    @DatabaseField(columnName = "angekommen_am", dataType = DataType.SERIALIZABLE)
    private LocalDateTime angekommenAm;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Rohrpostkapsel() {}

    public Rohrpostkapsel(String inhalt, Station vonStation, Station nachStation) {
        this.inhalt = inhalt;
        this.vonStation = vonStation;
        this.nachStation = nachStation;
        this.versendetAm = LocalDateTime.now();
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

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Station getVonStation() {
        return vonStation;
    }

    public void setVonStation(Station vonStation) {
        this.vonStation = vonStation;
    }

    public Station getNachStation() {
        return nachStation;
    }

    public void setNachStation(Station nachStation) {
        this.nachStation = nachStation;
    }

    public LocalDateTime getVersendetAm() {
        return versendetAm;
    }

    public void setVersendetAm(LocalDateTime versendetAm) {
        this.versendetAm = versendetAm;
    }

    public LocalDateTime getAngekommenAm() {
        return angekommenAm;
    }

    public void setAngekommenAm(LocalDateTime angekommenAm) {
        this.angekommenAm = angekommenAm;
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
        return "Rohrpostkapsel{" +
                "id=" + id +
                ", inhalt='" + inhalt + '\'' +
                ", versendetAm=" + versendetAm +
                ", angekommenAm=" + angekommenAm +
                '}';
    }
}
