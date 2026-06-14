package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Patient Entity
 * Repräsentiert einen Patienten im Krankenhaus
 */
@DatabaseTable(tableName = "patient")
public class Patient {

    @DatabaseField(generatedId = true, columnName = "patient_id")
    private Long id;

    @DatabaseField(canBeNull = false)
    private String vorname;

    @DatabaseField(canBeNull = false)
    private String nachname;

    @DatabaseField(columnName = "geburtsdatum", dataType = DataType.SERIALIZABLE)
    private LocalDate geburtsdatum;

    private String svnr;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "station_id")
    private Station station;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "zimmer_id")
    private Zimmer zimmer;

    @DatabaseField(columnName = "aufnahme_zeit", dataType = DataType.SERIALIZABLE)
    private LocalDateTime aufnahmeZeit;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Patient() {}

    public Patient(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getSvnr() {
        return svnr;
    }

    public void setSvnr(String svnr) {
        this.svnr = svnr;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Zimmer getZimmer() {
        return zimmer;
    }

    public void setZimmer(Zimmer zimmer) {
        this.zimmer = zimmer;
    }

    public LocalDateTime getAufnahmeZeit() {
        return aufnahmeZeit;
    }

    public void setAufnahmeZeit(LocalDateTime aufnahmeZeit) {
        this.aufnahmeZeit = aufnahmeZeit;
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
        return "Patient{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", svnr='" + svnr + '\'' +
                '}';
    }
}
