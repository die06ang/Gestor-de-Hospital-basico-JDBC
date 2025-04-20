package org.example.models;

/**
 * Clase Doctor
 */
public class Doctor {
    private int id;
    private String name;
    private String lastname;
    private String dni;
    private double salary;
    private String speciality;

    //Constructors, getters, setters and toString

    public Doctor() {
    }

    /**
     * Constructor de la clase Doctor, pasado por parámetros
     *
     * @param id
     * @param name
     * @param lastname
     * @param dni
     * @param salary
     * @param speciality
     */
    public Doctor(int id, String name, String lastname, String dni, double salary, String speciality) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.salary = salary;
        this.speciality = speciality;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * toString de la clase Doctor
     *
     * @return
     */
    @Override
    public String toString() {
        return "El doctor " + name + " " + lastname + " con dni: " + dni + " tiene un salario de: " + salary + " y su especialidad es: " + speciality;
    }
}
