package com.example.but05;

import org.json.JSONException;
import org.json.JSONObject;

public class Functions {
    private static int id;
    private static int idCompetence;
    private static int idUe;
    private static int semestre;
    private static int numero;
    private static String specialite;
    private static String parcours;
    private static String nom;
    private static String cours;
    private static String td;
    private static String tp;
    private static String description;

    public static int getId(JSONObject jObject) {
        try {
            id = jObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    /*private static int getIdUe(JSONObject jObject) {
        idUe = jObject.getInt("Id");
        return idUe;
    }*/

    public static int getIdCompetence(JSONObject jObject) {
        try {
            idCompetence = jObject.getInt("idCompetence");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idCompetence;
    }

    public static int getSemestre(JSONObject jObject) {
        try {
            semestre = jObject.getInt("semestre");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return semestre;
    }

    public static int getNumero(JSONObject jObject) {
        try {
            numero = jObject.getInt("numero");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return numero;
    }

    public static String getSpecialite(JSONObject jObject) {
        try {
            specialite = jObject.getString("specialite");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return specialite;
    }
    public static String getCours(JSONObject jObject) {
        try {
            cours = jObject.getString("cours");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cours;
    }
    public static String getTd(JSONObject jObject) {
        try {
            td = jObject.getString("td");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return td;
    }
    public static String getTp(JSONObject jObject) {
        try {
            tp = jObject.getString("tp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tp;
    }
    public static String getDescription(JSONObject jObject) {
        try {
            description = jObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }
    public static String getNom(JSONObject jObject) {
        try {
            nom = jObject.getString("nom");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nom;
    }
    public static String getParcours(JSONObject jObject) {
        try {
            parcours = jObject.getString("parcours");
            if (parcours == "null") {
                parcours = "Tronc Commun";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parcours;
    }
}
