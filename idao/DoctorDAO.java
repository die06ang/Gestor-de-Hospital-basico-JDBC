package org.example.idao;

import org.example.models.Doctor;

import java.util.List;

/**
 * Interfaz Doctor
 */
public interface DoctorDAO {
    public int add(Doctor doc);

    public boolean delete(int id);

    public Doctor getDoctor(int id);

    public List<Doctor> getDoctors();

    public boolean update(Doctor doc);

    public Doctor getDoctorByPatientId(int patient_id);
}
