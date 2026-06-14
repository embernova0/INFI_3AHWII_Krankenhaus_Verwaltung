package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * Medikamentengabe Entity
 * Repräsentiert die Gabe eines Medikaments an einen Patienten
 */
@DatabaseTable(tableName = "medikamentengabe")
public class Medikamentengabe {

    @DatabaseField(generatedId = true, columnName = "gabe_id")
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "patient_id")
    private Patient patient;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "medikament_id")
    private Medikament medikament;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "schwester_id")
    private Krankenschwester krankenschwester;

    private String dosierung;

    @DatabaseField(columnName = "verabreicht_am", dataType = DataType.SERIALIZABLE)
    private LocalDateTime verabreichungszeit;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Medikamentengabe() {}

    public Medikamentengabe(Patient patient, Medikament medikament, Krankenschwester krankenschwester, String dosierung) {
        this.patient = patient;
        this.medikament = medikament;
        this.krankenschwester = krankenschwester;
        this.dosierung = dosierung;
        this.verabreichungszeit = LocalDateTime.now();
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medikament getMedikament() {
        return medikament;
    }

    public void setMedikament(Medikament medikament) {
        this.medikament = medikament;
    }

    public Krankenschwester getKrankenschwester() {
        return krankenschwester;
    }

    public void setKrankenschwester(Krankenschwester krankenschwester) {
        this.krankenschwester = krankenschwester;
    }

    public String getDosierung() {
        return dosierung;
    }

    public void setDosierung(String dosierung) {
        this.dosierung = dosierung;
    }

    public LocalDateTime getVerabreichungszeit() {
        return verabreichungszeit;
    }

    public void setVerabreichungszeit(LocalDateTime verabreichungszeit) {
        this.verabreichungszeit = verabreichungszeit;
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
        return "Medikamentengabe{" +
                "id=" + id +
                ", dosierung='" + dosierung + '\'' +
                ", verabreichungszeit=" + verabreichungszeit +
                '}';
    }
}
