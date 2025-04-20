package org.example;

import org.example.dao.DoctorDAOImpl;
import org.example.dao.PatientDAOImpl;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.repositories.PatientRepositoryImpl;

import java.util.List;

/**
 * Clase App, es la clase ejecutable/main
 */
public class App {
    public static void main(String[] args) {
        DoctorDAOImpl docDAO = new DoctorDAOImpl();
        PatientDAOImpl patDAO = new PatientDAOImpl();
        PatientRepositoryImpl patRepo = new PatientRepositoryImpl();

        //Añadir doctores
        Doctor doc = new Doctor(1, "Pepa", "Diez", "16777777N", 2345.6, "Dermatología");
        int f = docDAO.add(doc);
        System.out.println("Se han insertado " + f + " filas");

        Doctor doc2 = new Doctor(2, "Pepe", "Perez", "14545454J", 3544.5, "Cardiología");
        f = docDAO.add(doc2);
        System.out.println("Se han insertado " + f + " filas ");

        //Recuperamos un doctor por ID
        Doctor d = docDAO.getDoctor(1);
        if (d != null) {
            System.out.println(d.toString());
        }

        //Borrar un doctor por ID
        boolean b = docDAO.delete(1);
        if (b) {
            System.out.println("Se ha borrado correctamente");
        } else {
            System.out.println("No se ha borrado");
        }

        //Recuperar varios doctores
        List<Doctor> doctors = docDAO.getDoctors();
        for (Doctor doct : doctors) {
            System.out.println(doct.toString());
        }

        //Actualizar doctores
        doc2.setSpeciality("Dermatología");
        boolean b2 = docDAO.update(doc2);
        if (b2) {
            System.out.println("Actualización correcta");
        } else {
            System.out.println("Fallo en la actualización");
        }


        //Añadir pacientes
        Patient pat = new Patient(1, "Juan", "Gracía", "12345678A", "dolor de garganta", doc);
        f = patDAO.add(pat);
        System.out.println("Se han insertado " + f + " filas");

        Patient pat2 = new Patient(2, "María", "González", "87654321B", "dolor de espalda", doc2);
        f = patDAO.add(pat2);
        System.out.println("Se han insertado " + f + " filas ");

        //Recuperamos un paciente por ID
        Patient p = patDAO.getPatient(1);
        if (p != null) {
            System.out.println(p.toString());
        }

        //Borrar un paciente por ID
        b = patDAO.delete(1);
        if (b) {
            System.out.println("Se ha borrado correctamente");
        } else {
            System.out.println("No se ha borrado");
        }

        //Recuperar varios pacientes
        List<Patient> patients = patDAO.getPatients();
        for (Patient pati : patients) {
            System.out.println(pati.toString());
        }

        //Actualizar pacientes
        pat2.setDolencia("dolor de garganta");
        b2 = patDAO.update(pat2);
        if (b2) {
            System.out.println("Actualización correcta");
        } else {
            System.out.println("Fallo en la actualización");
        }

        //Repositorio de Pacientes
        //Asignar doctor a paciente
        Doctor doc3 = new Doctor(3, "Pepa", "Diez", "16777777N", 2345.6, "Dermatología");
        Patient pat3 = new Patient(3, "Juan", "Gracía", "12345678A", "dolor de garganta", doc3);
        patRepo.add(pat3);

        //Actualizar doctor de un paciente
        Patient pat4 = new Patient(4, "Juan 2", "Gracía", "12345678A", "dolor de garganta", doc3);
        patRepo.update(pat4);

        //Desasignar un doctor de un paciente
        patRepo.remove(pat4);
    }
}
