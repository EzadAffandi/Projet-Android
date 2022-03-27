package com.example.but05;

public class Ue {
    int id;
    int semestre;
    int numero;
    int idCompetence;
    String parcours;
    public Ue(int unId,int uneSemestre,int unNumero,int unIdCompetence,String unParcours){
        id=unId;
        semestre=uneSemestre;
        numero=unNumero;
        idCompetence=unIdCompetence;
        parcours=unParcours;
    }
    public int getId(){
        return this.id;
    }
    public int getSemestre(){
        return this.semestre;
    }
    public int getNumero(){
        return this.numero;
    }
    public int getIdCompetence(){
        return this.idCompetence;
    }
    public String getParcours(){
        return this.parcours;
    }
}
