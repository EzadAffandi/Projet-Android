package com.example.but05;

public class But {
    int id;
    String specialite;
    public But(int unId,String uneSpecialite){
        id=unId;
        specialite=uneSpecialite;
    }
    public int getId(){
        return this.id;
    }
    public String getSpecialite(){
        return this.specialite;
    }
}
