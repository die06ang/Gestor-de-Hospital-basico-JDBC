package org.example.idao;

import org.example.models.Doctor;
import org.example.models.Patient;

import java.util.List;

/**
 * Interfaz Paciente
 */
public interface PatientDAO {
    public int add(Patient pat);

    public boolean delete(int id);

    public Patient getPatient(int id);

    public List<Patient> getPatients();

    public boolean update(Patient pat);
}
