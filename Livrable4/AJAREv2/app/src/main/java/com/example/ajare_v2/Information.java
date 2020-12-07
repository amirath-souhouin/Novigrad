package com.example.ajare_v2;

public class Information {
    String id;
    String nom;
    String prenom;
    String mail;
    String number;
    public Information(String id,String prenom,String nom,String mail,String number){
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.number=number;
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getMail(){
        return mail;
    }
    public String getNumber(){
        return number;
    }
}
