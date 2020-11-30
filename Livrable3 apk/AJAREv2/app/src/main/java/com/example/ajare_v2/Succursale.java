package com.example.ajare_v2;

public class Succursale {
    private String addressName;
    String password;
    String email;
    String horaire;
    String numero;
    String id;
    public Succursale(){
    }
    public Succursale(String id,String addressName,String mail,String password,String numero,String horaire){
        this.numero = numero;
        this.addressName = addressName;
        email = mail;
        this.password = password;
        this.horaire = horaire;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setAddresseName(String addressName) {
        this.addressName = addressName;
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
        this.horaire = horaire;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getHoraire() {
        return horaire;
    }

}
