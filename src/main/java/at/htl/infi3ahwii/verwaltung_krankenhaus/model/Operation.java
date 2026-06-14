package at.htl.infi3ahwii.verwaltung_krankenhaus.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

/**
 * Operation Entity
 * Repräsentiert eine Operation im Krankenhaus
 */
@DatabaseTable(tableName = "operationen")
public class Operation {

    @DatabaseField(generatedId = true, columnName = "operation_id")
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "patient_id")
    private Patient patient;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "arzt_id")
    private Arzt arzt;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "schwester_id")
    private Krankenschwester krankenschwester;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "saal_id")
    private OpSaal opSaal;

    private String beschreibung;

    @DatabaseField(columnName = "geplant_start", dataType = DataType.SERIALIZABLE)
    private LocalDateTime geplantStart;

    @DatabaseField(columnName = "op_start", dataType = DataType.SERIALIZABLE)
    private LocalDateTime opStart;

    @DatabaseField(columnName = "op_ende", dataType = DataType.SERIALIZABLE)
    private LocalDateTime opEnde;

    @DatabaseField(columnName = "created_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime createdAt;

    @DatabaseField(columnName = "updated_at", dataType = DataType.SERIALIZABLE)
    private LocalDateTime updatedAt;

    // Konstruktoren
    public Operation() {}

    public Operation(Patient patient, Arzt arzt, Krankenschwester krankenschwester, OpSaal opSaal, String beschreibung) {
        this.patient = patient;
        this.arzt = arzt;
        this.krankenschwester = krankenschwester;
        this.opSaal = opSaal;
        this.beschreibung = beschreibung;
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

    public Arzt getArzt() {
        return arzt;
    }

    public void setArzt(Arzt arzt) {
        this.arzt = arzt;
    }

    public Krankenschwester getKrankenschwester() {
        return krankenschwester;
    }

    public void setKrankenschwester(Krankenschwester krankenschwester) {
        this.krankenschwester = krankenschwester;
    }

    public OpSaal getOpSaal() {
        return opSaal;
    }

    public void setOpSaal(OpSaal opSaal) {
        this.opSaal = opSaal;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public LocalDateTime getGeplantStart() {
        return geplantStart;
    }

    public void setGeplantStart(LocalDateTime geplantStart) {
        this.geplantStart = geplantStart;
    }

    public LocalDateTime getOpStart() {
        return opStart;
    }

    public void setOpStart(LocalDateTime opStart) {
        this.opStart = opStart;
    }

    public LocalDateTime getOpEnde() {
        return opEnde;
    }

    public void setOpEnde(LocalDateTime opEnde) {
        this.opEnde = opEnde;
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
        return "Operation{" +
                "id=" + id +
                ", beschreibung='" + beschreibung + '\'' +
                ", geplantStart=" + geplantStart +
                '}';
    }
}
