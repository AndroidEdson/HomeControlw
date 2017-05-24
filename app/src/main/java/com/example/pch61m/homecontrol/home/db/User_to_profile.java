package com.example.pch61m.homecontrol.home.db;

/**
 * Created by PCH61M on 24/05/2017.
 */

public final class User_to_profile {

    int id;
    int id_profile;
    String description ;
    String actuators;


    public User_to_profile(int id, int id_profile, String description, String actuators) {
        this.id = id;
        this.id_profile = id_profile;
        this.description = description;
        this.actuators = actuators;
    }


    public int getId() {
        return id;
    }


    public int getId_profile() {
        return id_profile;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActuators() {
        return actuators;
    }

    public void setActuators(String actuators) {
        this.actuators = actuators;
    }




}
