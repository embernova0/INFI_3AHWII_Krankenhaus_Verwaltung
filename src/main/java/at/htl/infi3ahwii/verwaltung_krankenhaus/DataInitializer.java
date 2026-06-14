package at.htl.infi3ahwii.verwaltung_krankenhaus;

import at.htl.infi3ahwii.verwaltung_krankenhaus.config.DatabaseHelper;
import at.htl.infi3ahwii.verwaltung_krankenhaus.model.*;
import com.j256.ormlite.dao.Dao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DataInitializer
 * Erstellt Testdaten beim Start der Anwendung
 * Alle Änderungen werden mit Timestamps protokolliert
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final DatabaseHelper databaseHelper;

    public DataInitializer(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Erstelle Testdaten für Krankenhausverwaltung ===");

        // Prüfen, ob bereits Daten existieren
        Dao<Station, Long> stationDao = databaseHelper.getDao(Station.class);
        if (stationDao.queryForAll().size() > 0) {
            System.out.println("=== Daten bereits vorhanden - lösche alle Daten und erstelle neu ===");
            // Alle Daten löschen
            deleteAllData();
        }

        // Stationen erstellen
        Station station1 = new Station("Innere Medizin", "Gebäude A");
        Station station2 = new Station("Chirurgie", "Gebäude B");
        Station station3 = new Station("Notaufnahme", "Gebäude C");
        Station station4 = new Station("Intensivstation", "Gebäude D");
        Station station5 = new Station("Kinderstation", "Gebäude E");
        
        stationDao.create(station1);
        stationDao.create(station2);
        stationDao.create(station3);
        stationDao.create(station4);
        stationDao.create(station5);
        
        System.out.println("Stationen erstellt: " + stationDao.queryForAll().size());

        // Zimmer erstellen
        Dao<Zimmer, Long> zimmerDao = databaseHelper.getDao(Zimmer.class);
        Zimmer zimmer1 = new Zimmer(station1, "101");
        Zimmer zimmer2 = new Zimmer(station1, "102");
        Zimmer zimmer3 = new Zimmer(station1, "103");
        Zimmer zimmer4 = new Zimmer(station1, "104");
        Zimmer zimmer5 = new Zimmer(station2, "201");
        Zimmer zimmer6 = new Zimmer(station2, "202");
        Zimmer zimmer7 = new Zimmer(station2, "203");
        Zimmer zimmer8 = new Zimmer(station2, "204");
        Zimmer zimmer9 = new Zimmer(station3, "301");
        Zimmer zimmer10 = new Zimmer(station3, "302");
        Zimmer zimmer11 = new Zimmer(station3, "303");
        Zimmer zimmer12 = new Zimmer(station3, "304");
        Zimmer zimmer13 = new Zimmer(station4, "401");
        Zimmer zimmer14 = new Zimmer(station4, "402");
        Zimmer zimmer15 = new Zimmer(station4, "403");
        Zimmer zimmer16 = new Zimmer(station4, "404");
        Zimmer zimmer17 = new Zimmer(station5, "501");
        Zimmer zimmer18 = new Zimmer(station5, "502");
        Zimmer zimmer19 = new Zimmer(station5, "503");
        Zimmer zimmer20 = new Zimmer(station5, "504");
        
        zimmerDao.create(zimmer1);
        zimmerDao.create(zimmer2);
        zimmerDao.create(zimmer3);
        zimmerDao.create(zimmer4);
        zimmerDao.create(zimmer5);
        zimmerDao.create(zimmer6);
        zimmerDao.create(zimmer7);
        zimmerDao.create(zimmer8);
        zimmerDao.create(zimmer9);
        zimmerDao.create(zimmer10);
        zimmerDao.create(zimmer11);
        zimmerDao.create(zimmer12);
        zimmerDao.create(zimmer13);
        zimmerDao.create(zimmer14);
        zimmerDao.create(zimmer15);
        zimmerDao.create(zimmer16);
        zimmerDao.create(zimmer17);
        zimmerDao.create(zimmer18);
        zimmerDao.create(zimmer19);
        zimmerDao.create(zimmer20);
        
        System.out.println("Zimmer erstellt: " + zimmerDao.queryForAll().size());

        // Ärzte erstellen (aus dump_infi.sql)
        Dao<Arzt, Long> arztDao = databaseHelper.getDao(Arzt.class);
        Arzt arzt1 = new Arzt("Thomas", "Maier", "Chirurgie");
        Arzt arzt2 = new Arzt("Petra", "Hofer", "Kardiologie");
        Arzt arzt3 = new Arzt("Michael", "Moser", "Orthopädie");
        Arzt arzt4 = new Arzt("Sarah", "Huber", "Anästhesie");
        Arzt arzt5 = new Arzt("Daniel", "Egger", "Neurologie");
        Arzt arzt6 = new Arzt("Markus", "Leitner", "Innere Medizin");
        Arzt arzt7 = new Arzt("Julia", "Schneider", "Kinderheilkunde");
        Arzt arzt8 = new Arzt("Florian", "Auer", "Traumatologie");
        Arzt arzt9 = new Arzt("Lisa", "Mayr", "Radiologie");
        Arzt arzt10 = new Arzt("David", "Pichler", "Intensivmedizin");
        
        arztDao.create(arzt1);
        arztDao.create(arzt2);
        arztDao.create(arzt3);
        arztDao.create(arzt4);
        arztDao.create(arzt5);
        arztDao.create(arzt6);
        arztDao.create(arzt7);
        arztDao.create(arzt8);
        arztDao.create(arzt9);
        arztDao.create(arzt10);
        
        System.out.println("Ärzte erstellt: " + arztDao.queryForAll().size());

        // Krankenschwestern erstellen (aus dump_infi.sql)
        Dao<Krankenschwester, Long> ksDao = databaseHelper.getDao(Krankenschwester.class);
        Krankenschwester ks1 = new Krankenschwester("Maria", "Gruber");
        Krankenschwester ks2 = new Krankenschwester("Julia", "Schmidt");
        Krankenschwester ks3 = new Krankenschwester("Anna", "Hofer");
        Krankenschwester ks4 = new Krankenschwester("Katharina", "Moser");
        Krankenschwester ks5 = new Krankenschwester("Lisa", "Mayr");
        Krankenschwester ks6 = new Krankenschwester("Eva", "Lechner");
        Krankenschwester ks7 = new Krankenschwester("Nina", "Auer");
        Krankenschwester ks8 = new Krankenschwester("Sandra", "Pichler");
        Krankenschwester ks9 = new Krankenschwester("Laura", "Egger");
        Krankenschwester ks10 = new Krankenschwester("Melanie", "Leitner");
        Krankenschwester ks11 = new Krankenschwester("Sophie", "Steiner");
        Krankenschwester ks12 = new Krankenschwester("Teresa", "Huber");
        Krankenschwester ks13 = new Krankenschwester("Johanna", "Fuchs");
        Krankenschwester ks14 = new Krankenschwester("Claudia", "Schwarz");
        Krankenschwester ks15 = new Krankenschwester("Vanessa", "Mair");
        
        ksDao.create(ks1);
        ksDao.create(ks2);
        ksDao.create(ks3);
        ksDao.create(ks4);
        ksDao.create(ks5);
        ksDao.create(ks6);
        ksDao.create(ks7);
        ksDao.create(ks8);
        ksDao.create(ks9);
        ksDao.create(ks10);
        ksDao.create(ks11);
        ksDao.create(ks12);
        ksDao.create(ks13);
        ksDao.create(ks14);
        ksDao.create(ks15);
        
        System.out.println("Krankenschwestern erstellt: " + ksDao.queryForAll().size());

        // Medikamente erstellen (aus dump_infi.sql)
        Dao<Medikament, Long> medikamentDao = databaseHelper.getDao(Medikament.class);
        Medikament med1 = new Medikament("Paracetamol", "Bayer");
        Medikament med2 = new Medikament("Ibuprofen", "Sandoz");
        Medikament med3 = new Medikament("Aspirin", "Bayer");
        Medikament med4 = new Medikament("Novalgin", "Sanofi");
        Medikament med5 = new Medikament("Voltaren", "Novartis");
        Medikament med6 = new Medikament("Amoxicillin", "Pfizer");
        Medikament med7 = new Medikament("Penicillin", "Sandoz");
        Medikament med8 = new Medikament("Morphin", "Baxter");
        Medikament med9 = new Medikament("Insulin", "Novo Nordisk");
        Medikament med10 = new Medikament("Pantoprazol", "Hexal");
        Medikament med11 = new Medikament("Metformin", "Merck");
        Medikament med12 = new Medikament("Clexane", "Sanofi");
        Medikament med13 = new Medikament("Ventolin", "GSK");
        Medikament med14 = new Medikament("Augmentin", "GSK");
        Medikament med15 = new Medikament("Tramal", "Stada");
        
        medikamentDao.create(med1);
        medikamentDao.create(med2);
        medikamentDao.create(med3);
        medikamentDao.create(med4);
        medikamentDao.create(med5);
        medikamentDao.create(med6);
        medikamentDao.create(med7);
        medikamentDao.create(med8);
        medikamentDao.create(med9);
        medikamentDao.create(med10);
        medikamentDao.create(med11);
        medikamentDao.create(med12);
        medikamentDao.create(med13);
        medikamentDao.create(med14);
        medikamentDao.create(med15);
        
        System.out.println("Medikamente erstellt: " + medikamentDao.queryForAll().size());

        // Patienten erstellen (aus dump_infi.sql)
        Dao<Patient, Long> patientDao = databaseHelper.getDao(Patient.class);
        Patient patient1 = new Patient("Max", "Mustermann");
        patient1.setSvnr("1234567890");
        patient1.setStation(station1);
        patient1.setZimmer(zimmer1);
        patient1.setGeburtsdatum(LocalDate.of(2000, 5, 10));
        patient1.setAufnahmeZeit(LocalDateTime.of(2025, 10, 12, 8, 30));
        
        Patient patient2 = new Patient("Anna", "Huber");
        patient2.setSvnr("2345678901");
        patient2.setStation(station2);
        patient2.setZimmer(zimmer5);
        patient2.setGeburtsdatum(LocalDate.of(1995, 3, 20));
        patient2.setAufnahmeZeit(LocalDateTime.of(2025, 10, 12, 9, 15));
        
        Patient patient3 = new Patient("Lukas", "Gruber");
        patient3.setSvnr("3456789012");
        patient3.setStation(station1);
        patient3.setZimmer(zimmer2);
        patient3.setGeburtsdatum(LocalDate.of(1988, 7, 15));
        patient3.setAufnahmeZeit(LocalDateTime.of(2025, 10, 13, 11, 0));
        
        Patient patient4 = new Patient("David", "Steiner");
        patient4.setSvnr("4567890123");
        patient4.setStation(station4);
        patient4.setZimmer(zimmer13);
        patient4.setGeburtsdatum(LocalDate.of(1975, 11, 22));
        patient4.setAufnahmeZeit(LocalDateTime.of(2025, 10, 13, 10, 15));
        
        Patient patient5 = new Patient("Sophie", "Auer");
        patient5.setSvnr("5678901234");
        patient5.setStation(station5);
        patient5.setZimmer(zimmer17);
        patient5.setGeburtsdatum(LocalDate.of(2002, 4, 12));
        patient5.setAufnahmeZeit(LocalDateTime.of(2025, 10, 13, 12, 20));
        
        Patient patient6 = new Patient("Michael", "Hofer");
        patient6.setSvnr("6789012345");
        patient6.setStation(station2);
        patient6.setZimmer(zimmer6);
        patient6.setGeburtsdatum(LocalDate.of(1965, 1, 3));
        patient6.setAufnahmeZeit(LocalDateTime.of(2025, 10, 14, 8, 0));
        
        Patient patient7 = new Patient("Julia", "Mayr");
        patient7.setSvnr("7890123456");
        patient7.setStation(station3);
        patient7.setZimmer(zimmer9);
        patient7.setGeburtsdatum(LocalDate.of(1998, 9, 19));
        patient7.setAufnahmeZeit(LocalDateTime.of(2025, 10, 14, 8, 20));
        
        Patient patient8 = new Patient("Markus", "Leitner");
        patient8.setSvnr("8901234567");
        patient8.setStation(station1);
        patient8.setZimmer(zimmer3);
        patient8.setGeburtsdatum(LocalDate.of(1980, 6, 30));
        patient8.setAufnahmeZeit(LocalDateTime.of(2025, 10, 14, 9, 0));
        
        Patient patient9 = new Patient("Teresa", "Egger");
        patient9.setSvnr("9012345678");
        patient9.setStation(station2);
        patient9.setZimmer(zimmer7);
        patient9.setGeburtsdatum(LocalDate.of(1992, 12, 5));
        patient9.setAufnahmeZeit(LocalDateTime.of(2025, 10, 14, 10, 30));
        
        Patient patient10 = new Patient("Florian", "Schwarz");
        patient10.setSvnr("0123456789");
        patient10.setStation(station4);
        patient10.setZimmer(zimmer14);
        patient10.setGeburtsdatum(LocalDate.of(1978, 8, 16));
        patient10.setAufnahmeZeit(LocalDateTime.of(2025, 10, 14, 11, 0));
        
        patientDao.create(patient1);
        patientDao.create(patient2);
        patientDao.create(patient3);
        patientDao.create(patient4);
        patientDao.create(patient5);
        patientDao.create(patient6);
        patientDao.create(patient7);
        patientDao.create(patient8);
        patientDao.create(patient9);
        patientDao.create(patient10);
        
        System.out.println("Patienten erstellt: " + patientDao.queryForAll().size());

        // OpSäle erstellen (aus dump_infi.sql)
        Dao<OpSaal, Long> opSaalDao = databaseHelper.getDao(OpSaal.class);
        OpSaal opSaal1 = new OpSaal("OP 1");
        OpSaal opSaal2 = new OpSaal("OP 2");
        OpSaal opSaal3 = new OpSaal("OP 3");
        OpSaal opSaal4 = new OpSaal("OP 4");
        OpSaal opSaal5 = new OpSaal("OP 5");
        
        opSaalDao.create(opSaal1);
        opSaalDao.create(opSaal2);
        opSaalDao.create(opSaal3);
        opSaalDao.create(opSaal4);
        opSaalDao.create(opSaal5);
        
        System.out.println("OpSäle erstellt: " + opSaalDao.queryForAll().size());

        // Operationen erstellen (aus dump_infi.sql)
        Dao<Operation, Long> operationDao = databaseHelper.getDao(Operation.class);
        Operation operation1 = new Operation(patient1, arzt1, ks1, opSaal1, "Blinddarmoperation");
        operation1.setGeplantStart(LocalDateTime.of(2025, 10, 15, 8, 0));
        operation1.setOpStart(LocalDateTime.of(2025, 10, 15, 8, 10));
        operation1.setOpEnde(LocalDateTime.of(2025, 10, 15, 9, 20));
        
        Operation operation2 = new Operation(patient2, arzt2, ks3, opSaal2, "Herzkatheter");
        operation2.setGeplantStart(LocalDateTime.of(2025, 10, 16, 9, 0));
        operation2.setOpStart(LocalDateTime.of(2025, 10, 16, 9, 5));
        operation2.setOpEnde(LocalDateTime.of(2025, 10, 16, 10, 45));
        
        Operation operation3 = new Operation(patient4, arzt10, ks4, opSaal3, "Notfalloperation");
        operation3.setGeplantStart(LocalDateTime.of(2025, 10, 16, 13, 0));
        operation3.setOpStart(LocalDateTime.of(2025, 10, 16, 13, 2));
        operation3.setOpEnde(LocalDateTime.of(2025, 10, 16, 14, 10));
        
        operationDao.create(operation1);
        operationDao.create(operation2);
        operationDao.create(operation3);
        
        System.out.println("Operationen erstellt: " + operationDao.queryForAll().size());

        // Rohrpostkapseln erstellen (aus dump_infi.sql)
        Dao<Rohrpostkapsel, Long> kapselDao = databaseHelper.getDao(Rohrpostkapsel.class);
        Rohrpostkapsel kapsel1 = new Rohrpostkapsel("Blutprobe", station1, station3);
        kapsel1.setVersendetAm(LocalDateTime.of(2025, 10, 12, 10, 15));
        kapsel1.setAngekommenAm(LocalDateTime.of(2025, 10, 12, 10, 18, 20));
        
        Rohrpostkapsel kapsel2 = new Rohrpostkapsel("Laborbefund", station3, station2);
        kapsel2.setVersendetAm(LocalDateTime.of(2025, 10, 12, 11, 10));
        kapsel2.setAngekommenAm(LocalDateTime.of(2025, 10, 12, 11, 13, 5));
        
        Rohrpostkapsel kapsel3 = new Rohrpostkapsel("Medikament", station2, station4);
        kapsel3.setVersendetAm(LocalDateTime.of(2025, 10, 12, 12, 0));
        kapsel3.setAngekommenAm(LocalDateTime.of(2025, 10, 12, 12, 2, 10));
        
        Rohrpostkapsel kapsel4 = new Rohrpostkapsel("Blutkonserve", station4, station1);
        kapsel4.setVersendetAm(LocalDateTime.of(2025, 10, 13, 7, 30));
        kapsel4.setAngekommenAm(LocalDateTime.of(2025, 10, 13, 7, 33, 40));
        
        kapselDao.create(kapsel1);
        kapselDao.create(kapsel2);
        kapselDao.create(kapsel3);
        kapselDao.create(kapsel4);
        
        System.out.println("Rohrpostkapseln erstellt: " + kapselDao.queryForAll().size());

        // SensorLogs erstellen (aus dump_infi.sql)
        Dao<SensorLog, Long> sensorLogDao = databaseHelper.getDao(SensorLog.class);
        SensorLog log1 = new SensorLog("ROHRPOST", "Kapsel 1 versendet");
        log1.setZeitpunkt(LocalDateTime.of(2025, 10, 12, 10, 15));
        SensorLog log2 = new SensorLog("ROHRPOST", "Kapsel 1 angekommen");
        log2.setZeitpunkt(LocalDateTime.of(2025, 10, 12, 10, 18, 20));
        SensorLog log3 = new SensorLog("ROHRPOST", "Kapsel 2 versendet");
        log3.setZeitpunkt(LocalDateTime.of(2025, 10, 12, 11, 10));
        SensorLog log4 = new SensorLog("ROHRPOST", "Kapsel 2 angekommen");
        log4.setZeitpunkt(LocalDateTime.of(2025, 10, 12, 11, 13, 5));
        SensorLog log5 = new SensorLog("TEMPERATUR", "Temperaturgrenze überschritten - Station 1");
        log5.setZeitpunkt(LocalDateTime.of(2025, 10, 12, 3, 15));
        SensorLog log6 = new SensorLog("TEMPERATUR", "Temperaturgrenze überschritten - Station 2");
        log6.setZeitpunkt(LocalDateTime.of(2025, 10, 15, 2, 45));
        SensorLog log7 = new SensorLog("TEMPERATUR", "Temperaturgrenze überschritten - Station 4");
        log7.setZeitpunkt(LocalDateTime.of(2025, 10, 16, 4, 20));
        
        sensorLogDao.create(log1);
        sensorLogDao.create(log2);
        sensorLogDao.create(log3);
        sensorLogDao.create(log4);
        sensorLogDao.create(log5);
        sensorLogDao.create(log6);
        sensorLogDao.create(log7);
        
        System.out.println("SensorLogs erstellt: " + sensorLogDao.queryForAll().size());

        // Temperaturalarme erstellen (aus dump_infi.sql)
        Dao<Temperaturalarm, Long> alarmDao = databaseHelper.getDao(Temperaturalarm.class);
        Temperaturalarm alarm1 = new Temperaturalarm(station1, 10.5, 8.0);
        alarm1.setAlarmzeit(LocalDateTime.of(2025, 10, 12, 3, 15));
        Temperaturalarm alarm2 = new Temperaturalarm(station2, 9.8, 8.0);
        alarm2.setAlarmzeit(LocalDateTime.of(2025, 10, 15, 2, 45));
        Temperaturalarm alarm3 = new Temperaturalarm(station4, 11.2, 8.0);
        alarm3.setAlarmzeit(LocalDateTime.of(2025, 10, 16, 4, 20));
        
        alarmDao.create(alarm1);
        alarmDao.create(alarm2);
        alarmDao.create(alarm3);
        
        System.out.println("Temperaturalarme erstellt: " + alarmDao.queryForAll().size());

        // Medikamentengaben erstellen (aus dump_infi.sql)
        Dao<Medikamentengabe, Long> gabeDao = databaseHelper.getDao(Medikamentengabe.class);
        Medikamentengabe gabe1 = new Medikamentengabe(patient1, med1, ks1, "500 mg");
        gabe1.setVerabreichungszeit(LocalDateTime.of(2025, 10, 12, 12, 0));
        Medikamentengabe gabe2 = new Medikamentengabe(patient1, med2, ks2, "400 mg");
        gabe2.setVerabreichungszeit(LocalDateTime.of(2025, 10, 12, 18, 0));
        Medikamentengabe gabe3 = new Medikamentengabe(patient2, med4, ks3, "1 Tablette");
        gabe3.setVerabreichungszeit(LocalDateTime.of(2025, 10, 12, 13, 0));
        Medikamentengabe gabe4 = new Medikamentengabe(patient3, med3, ks5, "500 mg");
        gabe4.setVerabreichungszeit(LocalDateTime.of(2025, 10, 13, 11, 30));
        Medikamentengabe gabe5 = new Medikamentengabe(patient4, med8, ks6, "10 mg");
        gabe5.setVerabreichungszeit(LocalDateTime.of(2025, 10, 13, 16, 0));
        Medikamentengabe gabe6 = new Medikamentengabe(patient5, med1, ks7, "500 mg");
        gabe6.setVerabreichungszeit(LocalDateTime.of(2025, 10, 14, 9, 0));
        Medikamentengabe gabe7 = new Medikamentengabe(patient6, med10, ks8, "250 mg");
        gabe7.setVerabreichungszeit(LocalDateTime.of(2025, 10, 14, 12, 0));
        Medikamentengabe gabe8 = new Medikamentengabe(patient7, med9, ks10, "12 IE");
        gabe8.setVerabreichungszeit(LocalDateTime.of(2025, 10, 14, 18, 0));
        
        gabeDao.create(gabe1);
        gabeDao.create(gabe2);
        gabeDao.create(gabe3);
        gabeDao.create(gabe4);
        gabeDao.create(gabe5);
        gabeDao.create(gabe6);
        gabeDao.create(gabe7);
        gabeDao.create(gabe8);
        
        System.out.println("Medikamentengaben erstellt: " + gabeDao.queryForAll().size());

        System.out.println("=== Testdaten erfolgreich erstellt ===");
    }

    private void deleteAllData() throws SQLException {
        System.out.println("=== Lösche alle Daten ===");
        // Alle Tabellen in der richtigen Reihenfolge löschen (wegen Foreign Keys)
        databaseHelper.getDao(Medikamentengabe.class).deleteBuilder().delete();
        databaseHelper.getDao(Operation.class).deleteBuilder().delete();
        databaseHelper.getDao(Rohrpostkapsel.class).deleteBuilder().delete();
        databaseHelper.getDao(SensorLog.class).deleteBuilder().delete();
        databaseHelper.getDao(Temperaturalarm.class).deleteBuilder().delete();
        databaseHelper.getDao(Patient.class).deleteBuilder().delete();
        databaseHelper.getDao(Medikament.class).deleteBuilder().delete();
        databaseHelper.getDao(OpSaal.class).deleteBuilder().delete();
        databaseHelper.getDao(Krankenschwester.class).deleteBuilder().delete();
        databaseHelper.getDao(Arzt.class).deleteBuilder().delete();
        databaseHelper.getDao(Zimmer.class).deleteBuilder().delete();
        databaseHelper.getDao(Station.class).deleteBuilder().delete();
        System.out.println("=== Alle Daten gelöscht ===");
    }
}
