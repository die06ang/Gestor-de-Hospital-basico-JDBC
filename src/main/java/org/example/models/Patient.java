package org.example.models;

/**
 * Clase Paciente
 */
public class Patient {
    private int id;
    private String name;
    private String lastname;
    private String dni;
    private String dolencia;
    private Doctor doctor;

    //Constructors, getters, setters and toString


    public Patient() {
    }

    /**
     * Constructor de la clase Patient, pasado por parámetros
     *
     * @param id
     * @param name
     * @param lastname
     * @param dni
     * @param dolencia
     * @param doctor
     */
    public Patient(int id, String name, String lastname, String dni, String dolencia, Doctor doctor) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.dolencia = dolencia;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDolencia() {
        return dolencia;
    }

    public void setDolencia(String dolencia) {
        this.dolencia = dolencia;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * toString de la clase Patient
     *
     * @return
     */
    @Override
    public String toString() {
        if (doctor != null) {
            return "El paciente " + name + " " + lastname + " con dni: " + dni + " tiene: " + dolencia + ", su doctor es: " +
                    Character.toLowerCase(doctor.toString().charAt(0)) + doctor.toString().substring(1);
        } else {
            return "El paciente " + name + " " + lastname + " con dni: " + dni + " tiene: " + dolencia;
        }
    }
}
