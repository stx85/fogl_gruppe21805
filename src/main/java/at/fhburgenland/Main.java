package at.fhburgenland;

import jakarta.persistence.*;
import java.util.List;

public class Main {

    private static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("person");

    public static void main(String[] args) {
        System.out.println("Test");
        /* TO DO
            -) Connect Database -> persistence.xml
            -) Klasse zur Tabelle erstellen! -> Person.java
            -) Create Methods for
                -) addPerson
                -) readPerson
                -) readAllPersons --> Ausgabe ganze Tabelle
                -) update Person
                -) delete Person
         */

        addPerson("Stefan", "Hahnl", 434);
    }

    public static void addPerson(String vorname, String nachname, int gehalt) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Person p = new Person(vorname, nachname, gehalt);
            em.persist(p);

            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }
}
