import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;


    //makes connection to the database
    public class DB {

        public String makeGETRequest(String urlName){
            BufferedReader rd = null;
            StringBuilder sb = null;
            String line = null;
            try {
                URL url = new URL(urlName);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                while ((line = rd.readLine()) != null)
                {
                    sb.append(line + '\n');
                }
                conn.disconnect();
                return sb.toString();
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (ProtocolException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return "";

        }

        public ArrayList<String> parseJSON(String jsonString){
            ArrayList<String> var = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(jsonString);

                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject curObject = array.getJSONObject(i);
                    var.add(curObject.getString("date"));
                    var.add(curObject.getString("time"));
                    var.add(curObject.getString("weight"));
                    var.add(curObject.getString("idMail"));
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            return var;
        }


        public static void main(String[] args) {
            DB rc = new DB();
            String response = rc.makeGETRequest("https://studev.groept.be/api/a21ib2a02/mailboxdummydataLocation/3" );
            System.out.println(response);
            System.out.println(rc.parseJSON(response));
        }
    }




