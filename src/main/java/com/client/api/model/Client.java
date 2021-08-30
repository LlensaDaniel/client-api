package com.client.api.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    public Client(String nombre, String apellido, String fechaNacimiento, Integer edad) {
        this.name = nombre;
        this.lastName = apellido;
        this.age = edad;
        parseToDate(fechaNacimiento);

    }

    private String name;
    private String lastName;
    private Integer age;
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBirthDateAsString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    private void parseToDate(String fechaNacimiento) {
        try {
            this.birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento);
        }
        catch (ParseException e) {
            this.birthDate = null;
        }
    }
}
