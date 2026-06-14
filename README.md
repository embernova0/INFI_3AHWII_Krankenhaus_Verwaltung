# Krankenhaus-Verwaltung – Tirol Kliniken Innsbruck
> INFI-Projekt | 3AHWII | HTL | 2025/26

---

## Ausgangssituation (IST)
Die Tirol Kliniken Innsbruck verwalten täglich hunderte Patienten, Operationen 
und Medikamentengaben. Aktuell gibt es keine zentrale Anwendung, die all diese 
Abläufe samt Zeitverlauf digital erfasst. Besonders das unterirdische 
Rohrpostsystem – über das Blutproben, Medikamente und Dokumente zwischen 
Stationen transportiert werden – wird bisher gar nicht digital protokolliert.

---

## Zielsetzung (SOLL)
Es soll ein Maven-Projekt in Java mit Spring Boot entstehen, das die grundlegenden 
Krankenhaus-Abläufe verwaltet. Der Benutzer kann Daten erfassen, bearbeiten 
und einsehen. Zeitstempel werden beim jeweiligen Benutzereingriff gespeichert.

---

## Anwendungsfälle

**1. Patient aufnehmen**
Der Benutzer kann einen neuen Patienten mit seinen Stammdaten erfassen und ihn 
einer Station sowie einem Zimmer zuweisen. Dabei wird gespeichert, wann die 
Aufnahme eingetragen wurde.

**2. Stationen verwalten**
Der Benutzer kann Stationen des Krankenhauses verwalten. Stationen sind fixe Daten 
und können nicht bearbeitet oder gelöscht werden. Sie dienen als Referenz für Patienten.

**3. Operation planen & durchführen**
Der Benutzer kann eine Operation für einen Patienten anlegen, einen Arzt und 
eine Krankenschwester zuweisen sowie einen Saal und einen geplanten Starttermin 
festlegen. Beim manuellen Starten und Beenden der OP wird jeweils der 
Zeitpunkt gespeichert.

---

## KANN-Anwendungen

**KANN 1 – Rohrpost-Sensor:**
Der Benutzer kann eine Kapsel mit einem Inhalt (z.B. Blutprobe) von einer 
Station zu einer anderen schicken. Beim Abschicken und beim Bestätigen der 
Ankunft wird jeweils der Zeitpunkt gespeichert – daraus ergibt sich die 
Lieferdauer.

**KANN 2 – Medikamentenverwaltung:**
Eine Krankenschwester kann einem Patienten ein Medikament mit einer Dosierung 
zuordnen. Beim Eintragen wird gespeichert, wann und von wem die Gabe 
durchgeführt wurde.

---

## Technologien
| Was | Womit |
|---|---|
| Sprache | Java 21 |
| Datenbank | SQLite |
| ORM | ORMLite |
| Framework | Spring Boot 3.2.0 |
| Build | Maven |
| GUI (Anzeige) | Thymeleaf (Web) |
| IDE | Eclipse |
| Versionsverwaltung | Git / GitHub |

---

## Projektstruktur
```
src/main/java/at/htl/infi3ahwii/verwaltung_krankenhaus/
├── model/        → Datenbank-Entitäten
├── repository/   → Datenbankzugriff via ORMLite
├── service/      → Geschäftslogik
├── controller/   → Web-Controller
├── config/       → Datenbank-Konfiguration
└── DataInitializer → Testdaten
```

---

## Projekt-Repository: https://github.com/embernova0/INFI_3AHWII_Krankenhaus_Verwaltung#krankenhaus-verwaltung--tirol-kliniken-innsbruck
