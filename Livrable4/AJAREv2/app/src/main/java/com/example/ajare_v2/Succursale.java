package com.example.ajare_v2;

public class Succursale {
    private String ZipCode;
    String Service_Type;
    String password;
    String email;
    String Horaire;
    String numero;
    String id;
    public Succursale(){
    }
    public Succursale(String id,String serviceType,String addressName,String mail,String password,String numero,String horaire){
        this.numero = numero;
        this.ZipCode = addressName;
        this.Service_Type = serviceType;
        email = mail;
        this.password = password;
        this.Horaire = horaire;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setServiceType(String serviceType) {
            this.Service_Type = serviceType;

    }

    public String getService_Type() {
        return Service_Type;
    }

    public void setAddresseName(String addressName) {
        this.ZipCode = addressName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHoraire(String horaire) {
        this.Horaire = horaire;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public String getHoraire() {
        return Horaire;
    }

}
