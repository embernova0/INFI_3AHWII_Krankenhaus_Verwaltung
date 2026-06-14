package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * Arzt Entity
 * Repräsentiert einen Arzt im Krankenhaus
 */
@DatabaseTable(tableName = "arzt")
public class Arzt {

    @DatabaseField(generatedId = true, columnName = "arzt_id")
    private Long id;

    @DatabaseField(canBeNull = false)
    private String vorname;

    @DatabaseField(canBeNull = false)
    private String nachname;

    private String fachgebiet;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Arzt() {}

    public Arzt(String vorname, String nachname, String fachgebiet) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.fachgebiet = fachgebiet;
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

    public String getFachgebiet() {
        return fachgebiet;
    }

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
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
        return "Arzt{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", fachgebiet='" + fachgebiet + '\'' +
                '}';
    }
}
