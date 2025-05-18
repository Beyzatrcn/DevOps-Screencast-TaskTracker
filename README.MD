# DevOps Screencast – TaskTracker

Dies ist ein einfaches Projektmanagement-Tool (Mini-Aufgabenliste), das im Rahmen des DevOps-Lernjournals erstellt wurde.

## 🛠️ Technologien

- Java 21
- Spring Boot 3.3.5
- Gradle 8.10.2
- Postman (für API-Tests)
- Git (lokal)

---

## ✅ Aktuell implementierte Funktionen

### POST `/tasks`
- Erstellt eine neue Aufgabe
- Beispielanfrage (JSON):
  ```json
  {
    "name": "Erste Aufgabe",
    "status": "Offen"
  }
  ```

### GET `/tasks`
- Gibt eine Liste aller Aufgaben zurück

---

## 🧪 Getestet mit Postman

### Beispiel-Test für POST:
- Request: `POST http://localhost:8080/tasks`
- Body (raw → JSON):
  ```json
  {
    "name": "Zweite Aufgabe",
    "status": "Offen"
  }
  ```
### 🔹 POST: Neue Aufgabe erstellen

![Beispiel POST](Screenshots/1_Beispiel_Test_für%20POST.png)

### Beispiel-Test für GET:
- Request: `GET http://localhost:8080/tasks`
- Erwartete Response: Liste mit allen erstellten Aufgaben im JSON-Format

### 🔹 GET: Aufgaben abfragen

![Beispiel GET](Screenshots/2_Beispiel_Test_für_GET.png)




