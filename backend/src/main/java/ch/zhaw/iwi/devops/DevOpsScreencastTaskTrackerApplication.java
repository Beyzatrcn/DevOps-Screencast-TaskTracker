package ch.zhaw.iwi.devops;

// Importiert notwendige Klassen von Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation, die die Anwendung als Spring Boot Anwendung kennzeichnet
@SpringBootApplication
public class DevOpsScreencastTaskTrackerApplication {

    // Haupteinstiegspunkt der Anwendung
    public static void main(String[] args) {
        // Startet die Spring Boot Anwendung
        SpringApplication.run(DevOpsScreencastTaskTrackerApplication.class, args);
    }
}