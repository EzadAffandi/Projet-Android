package com.example.but05;

public class Ressource {
    int id;
    String nom;
    String cours;
    String td;
    String tp;
    String description;

    public Ressource(int unId,String unNom,String unCours,String unTd,String unTp){
        id=unId;
        nom=unNom;
        cours=unCours;
        td=unTd;
        tp=unTp;
        description=null;
    }

    public int getId(){
        return this.id;
    }
    public String getNom(){
        return this.nom;
    }
    public String getCours(){
        return this.cours;
    }
    public String getTd(){
        return this.td;
    }
    public String getTp(){
        return this.tp;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String uneDescription){
        this.description=uneDescription;
    }
}
