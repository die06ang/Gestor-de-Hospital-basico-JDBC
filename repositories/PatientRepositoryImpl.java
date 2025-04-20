package org.example.repositories;

import org.example.dao.DoctorDAOImpl;
import org.example.dao.PatientDAOImpl;
import org.example.models.Doctor;
import org.example.models.Patient;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Types.NULL;

/**
 * Implementación de la interfaz PatientRepository en la clase PatientRepositoryImpl
 */
public class PatientRepositoryImpl implements PatientRepository {
    private PatientDAOImpl patientDao;
    private DoctorDAOImpl doctorDao;
    Connection con = DatabaseConnection.getConnection();

    public PatientRepositoryImpl() {
        patientDao = new PatientDAOImpl();
        doctorDao = new DoctorDAOImpl();
    }

    /**
     * Relaciona un doctor concreto a un paciente concreto
     *
     * @param patient
     * @return
     */
    @Override
    public boolean add(Patient patient) {
        int idPaciente = patient.getId();
        if (patientDao.ifExitsPatient(idPaciente)) {
            String query = "UPDATE patients SET idDoctor = ? WHERE id = ?";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setObject(1, patient.getDoctor().getId());
                ps.setObject(2, patient.getId());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rs > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Actualiza la relación entre doctor y paciente
     *
     * @param patient
     * @return
     */
    @Override
    public boolean update(Patient patient) {
        if (patientDao.ifExitsPatient(patient.getId())) {
            String query = "UPDATE patients SET idDoctor=? WHERE id = ?";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setObject(1, patient.getDoctor().getId());
                ps.setObject(2, patient.getId());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rs > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Desasignar un doctor de un paciente
     *
     * @param patient
     * @return
     */
    @Override
    public boolean remove(Patient patient) {
        patient.setDoctor(null);
        if (patientDao.ifExitsPatient(patient.getId())) {
            String query = "UPDATE patients SET idDoctor = NULL WHERE id = ?";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setInt(1, patient.getId());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rs > 0) {
                return true;
            }
        }
        return false;
    }
}
