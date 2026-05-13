# Krankenhaus-Verwaltung – Tirol Kliniken Innsbruck

> INFI-Projekt | 3AHWII | HTL | 2025/26

---

## Ausgangssituation (IST)

Die Tirol Kliniken Innsbruck verwalten täglich hunderte Patienten, Operationen und Medikamentengaben. Aktuell gibt es keine zentrale Anwendung, die all diese Abläufe samt Zeitverlauf (wann wurde was gemacht?) digital erfasst. Besonders das unterirdische Rohrpostsystem – über das Blutproben, Medikamente und Dokumente zwischen Stationen transportiert werden – wird bisher gar nicht digital protokolliert.

---

## Zielsetzung (SOLL)

Eine Java-Desktop-Anwendung soll die wichtigsten Krankenhaus-Abläufe verwalten und den gesamten Zeitverlauf automatisch dokumentieren. Zusätzlich wird das Rohrpostsystem der Klinik über zwei CAN-Sensoren simuliert.

---

## Anwendungsfälle

**1. Patient aufnehmen**
Man kann einen neuen Patienten mit seinen Stammdaten erfassen, ihn einer Station und einem Zimmer zuweisen. Das System speichert automatisch, wann der Patient aufgenommen und wann er entlassen wurde.

**2. Operation planen & durchführen**
Man kann eine Operation für einen Patienten planen, einen Arzt und eine Krankenschwester zuweisen und einen Saal festlegen. Das System protokolliert automatisch, wann die OP geplant war, wann sie tatsächlich gestartet wurde und wann sie geendet hat.

**3. Rohrpostkapsel versenden**
Man kann eine Kapsel mit einem bestimmten Inhalt (z. B. Blutprobe) von einer Station zu einer anderen schicken. Das System erfasst automatisch via CAN-Sensoren, wann die Kapsel abgeschickt wurde und wann sie angekommen ist.

---

## CAN-Anwendungen

**CAN 1 – Abgangs-Sensor:** Sobald eine Kapsel in den Einwurf-Schacht eingelegt wird, feuert ein simulierter Lichtschrankensensor ein CAN-Signal. Das System setzt daraufhin automatisch den Zeitstempel `abgeschicktAm` und den Status `IN_TRANSIT`.

**CAN 2 – Ankunfts-Sensor:** Sobald die Kapsel an der Zielstation ankommt, löst ein simulierter Drucksensor ein CAN-Signal aus. Das System setzt automatisch den Zeitstempel `angekommenAm` und den Status `ANGEKOMMEN`. Aus beiden Zeitstempeln lässt sich die Lieferdauer berechnen.

---
