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
Es soll ein Maven-Projekt in Java entstehen, das die grundlegenden 
Krankenhaus-Abläufe verwaltet. Der Benutzer kann Daten erfassen, bearbeiten 
und einsehen. Zeitstempel werden beim jeweiligen Benutzereingriff gespeichert. 
Zwei Sensoren werden simuliert und deren Ausgabe in einer kleinen 
JavaFX-Ansicht dargestellt.

---

## Anwendungsfälle

**1. Patient aufnehmen**
Der Benutzer kann einen neuen Patienten mit seinen Stammdaten erfassen und ihn 
einer Station sowie einem Zimmer zuweisen. Dabei wird gespeichert, wann die 
Aufnahme eingetragen wurde.

**2. Operation planen & durchführen**
Der Benutzer kann eine Operation für einen Patienten anlegen, einen Arzt und 
eine Krankenschwester zuweisen sowie einen Saal und einen geplanten Starttermin 
festlegen. Beim manuellen Starten und Beenden der OP wird jeweils der 
Zeitpunkt gespeichert.

**3. Rohrpostkapsel versenden**
Der Benutzer kann eine Kapsel mit einem Inhalt (z.B. Blutprobe) von einer 
Station zu einer anderen schicken. Beim Abschicken und beim Bestätigen der 
Ankunft wird jeweils der Zeitpunkt gespeichert – daraus ergibt sich die 
Lieferdauer.

**4. Medikament verabreichen**
Eine Krankenschwester kann einem Patienten ein Medikament mit einer Dosierung 
zuordnen. Beim Eintragen wird gespeichert, wann und von wem die Gabe 
durchgeführt wurde.

---

## KANN-Anwendungen

**KANN 1 – Rohrpost-Sensor:**
Es wird ein Sensor simuliert, der den Abgang und die Ankunft einer 
Rohrpostkapsel erkennt. Der Benutzer löst den jeweiligen Schritt aus, 
der Sensor liefert daraufhin den Zeitstempel, der in der Datenbank 
gespeichert wird.

**KANN 2 – Temperatursensor (Medikamentenlager):**
Es wird ein Sensor simuliert, der die Temperatur im Medikamentenkühlschrank 
überwacht. Überschreitet der Wert einen Grenzwert, wird ein Alarm mit 
Zeitstempel und betroffener Station gespeichert. Die Ausgabe beider Sensoren 
ist in einer einfachen JavaFX-Ansicht sichtbar.

---

## Technologien
| Was | Womit |
|---|---|
| Sprache | Java 17 |
| Datenbank | MySQL |
| ORM | ORMLite 6.1 |
| Build | Maven |
| GUI (Anzeige) | JavaFX |
| IDE | Eclipse |
| Versionsverwaltung | Git / GitHub |

---

## Projektstruktur
```
src/main/java/at/htl/infi3ahwii/
├── model/        → Datenbank-Entitäten
├── repository/   → Datenbankzugriff via ORMLite
├── service/      → Geschäftslogik
├── sensor/       → Sensor-Simulation
└── ui/           → JavaFX-Ansicht
```

---

## Projekt-Repository: https://github.com/embernova0/INFI_3AHWII_Krankenhaus_Verwaltung#krankenhaus-verwaltung--tirol-kliniken-innsbruck
