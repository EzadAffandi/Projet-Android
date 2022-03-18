import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class Functions {
    public StringBuilder parseListBUT(String input) {

        JSONArray jArray;
        JSONObject jObject;
        StringBuilder toShow;

        toShow= new StringBuilder("");

        try {
            jArray= new JSONArray(input);
            for (int i= 0; i< jArray.length(); i++) {
                jObject= jArray.getJSONObject(i);
                toShow.append(jObject.getString("specialite"));
                toShow.append("\n");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toShow;
    }
}
