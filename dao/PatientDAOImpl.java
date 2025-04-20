package org.example.dao;

import org.example.idao.PatientDAO;
import org.example.models.Patient;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

/**
 * Implementación de PatientDAO en la clase PatientDAOImpl
 */
public class PatientDAOImpl implements PatientDAO {
    Connection con = DatabaseConnection.getConnection();

    /**
     * Añade un paciente a la base de datos, devolviendo el número de filas insertadas
     *
     * @param pat
     * @return
     */
    @Override
    public int add(Patient pat) {
        //Añadir idDoctor
        if (!ifExitsPatient(pat.getId())) {
            String query = "INSERT INTO patients (name, lastname, dni, dolencia, idDoctor) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setString(1, pat.getName());
                ps.setString(2, pat.getLastname());
                ps.setString(3, pat.getDni());
                ps.setString(4, pat.getDolencia());
                ps.setNull(5, NULL);
                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rs > 0) {
                return rs;
            }
        }
        return 0;
    }

    /**
     * Borra un paciente de la base de datos devolviendo un booleano según el éxito de la sentencia
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        if (ifExitsPatient(id)) {
            String query = "DELETE patients WHERE id = ?";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setInt(1, id);

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
     * Devuelve un paciente de la base de datos según un id pasado
     *
     * @param id
     * @return
     */
    @Override
    public Patient getPatient(int id) {
        String query = "select id, dni, lastname, name, dolencia from patients where id= ?";
        PreparedStatement ps = null;
        boolean check = false;
        Patient pat = new Patient();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
                pat.setId(rs.getInt("id"));
                pat.setDni(rs.getString("dni"));
                pat.setLastname(rs.getString("lastname"));
                pat.setName(rs.getString("name"));
                pat.setDolencia(rs.getString("dolencia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return pat;
        } else {
            return null;
        }
    }

    /**
     * Devuelve todos los pacientes en forma de "lista"
     *
     * @return
     */
    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        String query = "select * from patients";
        PreparedStatement ps = null;
        boolean check = false;

        try {
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                check = true;
                patient.setId(rs.getInt("id"));
                patient.setDni(rs.getString("dni"));
                patient.setLastname(rs.getString("lastname"));
                patient.setName(rs.getString("name"));
                patient.setDolencia(rs.getString("dolencia"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return patients;
        } else {
            return null;
        }
    }

    /**
     * Actualiza los campos de un paciente en la base de datos
     *
     * @param pat
     * @return
     */
    @Override
    public boolean update(Patient pat) {
        if (ifExitsPatient(pat.getId())) {
            String query = "UPDATE patients SET name = ?, lastname = ?, dni = ?, dolencia = ? WHERE id = ?";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setString(1, pat.getName());
                ps.setString(2, pat.getLastname());
                ps.setString(3, pat.getDni());
                ps.setString(4, pat.getDolencia());
                ps.setInt(5, pat.getId());

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
     * Verifica si el id pasado existe en la base de datos, devolviendo un booleano según la condición
     *
     * @param id
     * @return
     */
    public boolean ifExitsPatient(int id) {
        if (getPatient(id) == null) {
            return false;
        } else {
            return true;
        }
    }
}
