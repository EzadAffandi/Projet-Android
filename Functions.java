import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class Functions {
    public static String[] parseListeBUT(String input) {

        JSONArray jArray;
        JSONObject jObject;
        String[] listeBUT = {};

        try {
            jArray= new JSONArray(input);
            for (int i= 0; i< jArray.length(); i++) {
                jObject= jArray.getJSONObject(i);
                listeBUT[i]= jObject.getString("specialite");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listeBUT;
    }
    
     public static int[] parseBUTId(String input) {
        JSONArray jArray;
        JSONObject jObject;
        int[] idArray = {};

        try {
            jArray = new JSONArray(input);
            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);
                idArray[i] = jObject.getInt("id");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idArray;
    }

}
