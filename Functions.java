package com.example.but011;


import org.json.JSONException;
import org.json.JSONObject;

public class Functions {
    private static int id;
    private static int idCompetence;
    private static int idUe;
    private static String specialite;
    private static String parcours;
    private static String nom;
    private static String cours; //it is hours but in string
    private static String td; //it is hours but in string
    private static String tp; //it is hours but in string
    private static String description;
    private static String projet; //it is hours but in string

    public static int getId(JSONObject jObject) {
        try {
            id = jObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getSpecialite(JSONObject jObject) {
        try {
            specialite = jObject.getString("specialite");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return specialite;
    }

    private static int getIdUe(JSONObject jObject) {
        idUe = jObject.getInt("Id")
    }

    public static int getIdCompetence(JSONObject jObject) {
        try {
            idCompetence = jObject.getInt("idCompetence");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idCompetence;
    }

    public static String getSpecialite(JSONObject jObject) {
        try {
            specialite = jObject.getString("specialite");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return specialite;
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
    public static String getNom(JSONObject jObject) {
        try {
            nom = jObject.getString("nom");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nom;
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
        try  {
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
    public static String getProjet(JSONObject jObject) {
        try {
            projet = jObject.getString("projet");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return projet;
    }
    
}