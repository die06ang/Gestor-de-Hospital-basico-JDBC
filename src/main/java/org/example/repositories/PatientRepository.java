package org.example.repositories;

import org.example.models.Patient;

/**
 * Interfaz de PatientRepository
 */
public interface PatientRepository {

    boolean add(Patient patient);

    boolean update(Patient patient);

    boolean remove(Patient patient);
}
