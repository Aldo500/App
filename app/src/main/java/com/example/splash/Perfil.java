package com.example.splash;

import java.io.Serializable;

public class Perfil implements Serializable{
    private String namePerfil;
    private String passPerfil;
    private int image;
    private String red;

    public Perfil(){

    }

    public String getNamePerfil() {
        return namePerfil;
    }

    public void setNamePerfil(String namePerfil) {
        this.namePerfil = namePerfil;
    }

    public String getPassPerfil() {
        return passPerfil;
    }

    public void setPassPerfil(String passPerfil) {
        this.passPerfil = passPerfil;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }
}

