package at.fhburgenland;

import jakarta.persistence.*;

import java.util.ArrayList;
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

        //addPerson("Stefan", "Hahnl", 434);
        //readAll();
        readPerson(1);

        updatePerson(1, "dere", "hawe", 666);

        readPerson(1);
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

    private static void readPerson(int pnr) {
        EntityManager em = EMF.createEntityManager();

        Person p = null;

        try {
            p = em.find(Person.class, pnr);
            System.out.println(p.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void updatePerson(int pnr, String vorname, String nachname, int gehalt) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Person p = em.find(Person.class, pnr);
            p.setVorname(vorname);
            p.setNachname(nachname);
            p.setGehalt(gehalt);

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

    private static void removePerson(int pnr) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Person p = em.find(Person.class, pnr);

            em.remove(p);

            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public static void readAll() {
        EntityManager em = EMF.createEntityManager();

        String query = "SELECT p FROM Person p";

        TypedQuery<Person> tq = em.createQuery(query, Person.class);

        List<Person> pList = null;

        try {
            pList = tq.getResultList();

            pList.forEach(person -> System.out.println(
                    person.toString()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
