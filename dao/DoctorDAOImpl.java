package org.example.dao;

import org.example.idao.DoctorDAO;
import org.example.models.Doctor;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implantación de DoctorDAO en la clase PatientDAOImpl
 */
public class DoctorDAOImpl implements DoctorDAO {
    Connection con = DatabaseConnection.getConnection();

    /**
     * Añade un doctor a la base de datos, devolviendo el número de filas insertadas
     *
     * @param doc
     * @return
     */
    @Override
    public int add(Doctor doc) {
        if (!ifExitsDoctor(doc.getId())) {
            String query = "INSERT INTO doctors (name, lastname, dni, salary, speciality) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setString(1, doc.getName());
                ps.setString(2, doc.getLastname());
                ps.setString(3, doc.getDni());
                ps.setDouble(4, doc.getSalary());
                ps.setString(5, doc.getSpeciality());

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
     * Borra un doctor de la base de datos devolviendo un booleano según el éxito de la sentencia
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        if (ifExitsDoctor(id)) {
            String query = "DELETE doctors WHERE id = ?";
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
        } else {
            return false;
        }
        return false;
    }

    /**
     * Devuelve un doctor de la base de datos según un id pasado
     *
     * @param id
     * @return
     */
    @Override
    public Doctor getDoctor(int id) {
        String query = "select * from doctors where id= ?";
        PreparedStatement ps = null;
        boolean check = false;
        Doctor doctor = new Doctor();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
                doctor.setId(rs.getInt("id"));
                doctor.setDni(rs.getString("dni"));
                doctor.setLastname(rs.getString("lastname"));
                doctor.setName(rs.getString("name"));
                doctor.setSalary(rs.getDouble("salary"));
                doctor.setSpeciality(rs.getString("speciality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return doctor;
        } else {
            return null;
        }
    }

    /**
     * Devuelve todos los doctores en forma de "lista"
     *
     * @return
     */
    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        String query = "select * from doctors";
        PreparedStatement ps = null;
        boolean check = false;

        try {
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                check = true;
                doctor.setId(rs.getInt("id"));
                doctor.setDni(rs.getString("dni"));
                doctor.setLastname(rs.getString("lastname"));
                doctor.setName(rs.getString("name"));
                doctor.setSalary(rs.getDouble("salary"));
                doctor.setSpeciality(rs.getString("speciality"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return doctors;
        } else {
            return null;
        }
    }

    /**
     * Actualiza los campos de un doctor en la base de datos
     *
     * @param doc
     * @return
     */
    @Override
    public boolean update(Doctor doc) {
        if (ifExitsDoctor(doc.getId())) {
            String query = "UPDATE doctors SET name = ?, lastname = ?, dni = ?, salary = ?, speciality = ? WHERE id = ?";
            PreparedStatement ps = null;
            int rs = 0;
            try {
                ps = con.prepareStatement(query);
                ps.setString(1, doc.getName());
                ps.setString(2, doc.getLastname());
                ps.setString(3, doc.getDni());
                ps.setDouble(4, doc.getSalary());
                ps.setString(5, doc.getSpeciality());
                ps.setInt(6, doc.getId());

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
     * Devuelve un doctor que está asignado a un paciente concreto, pasando un id
     *
     * @param patient_id
     * @return
     */
    @Override
    public Doctor getDoctorByPatientId(int patient_id) {
        String query = "SELECT * FROM doctors INNER JOIN patients ON doctors.id = patients.idDoctor WHERE patients.id = ?";
        PreparedStatement ps = null;
        boolean check = false;
        Doctor doctor = new Doctor();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, patient_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
                doctor.setId(rs.getInt("id"));
                doctor.setDni(rs.getString("dni"));
                doctor.setLastname(rs.getString("lastname"));
                doctor.setName(rs.getString("name"));
                doctor.setSalary(rs.getDouble("salary"));
                doctor.setSpeciality(rs.getString("speciality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (check == true) {
            return doctor;
        } else {
            return null;
        }
    }

    /**
     * Verifica si el id pasado existe en la base de datos, devolviendo un booleano según la condición
     *
     * @param id
     * @return
     */
    public boolean ifExitsDoctor(int id) {
        if (getDoctor(id) == null) {
            return false;
        } else {
            return true;
        }
    }
}
