package it.xonne.cisitasample2020.models;

import java.util.Date;

public class User extends Object {

    public String name = "";
    public String surname = "";
    public int age = 0;
    public Date date;
    public boolean visibile = true;

    public User() {

    }


    public String getFullName() {
        return name + " " + surname;
    }


}
