import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class    Pars {
    private static HttpURLConnection connection;

   public static void main(String[] args) throws IOException {

       try {
            URL = new URL()
       }


/*
try{

    URL url = new URL("https://www.metaweather.com/api/location/search/?query=London");
//       URL url = new URL("https://api.gismeteo.net/v2/weather/current/4368/");

       HttpURLConnection con = (HttpURLConnection) url.openConnection();
       con.setRequestMethod("GET");
       con.connect();

       //Check if connect is made
       int responseCode = con.getResponseCode();

       // 200 OK
       if (responseCode != 200) {
           throw new RuntimeException("HttpResponseCode: " + responseCode);
       }
       else {

           StringBuilder informationString = new StringBuilder();
           Scanner scanner = new Scanner(url.openStream());

           while (scanner.hasNext()) {
               informationString.append(scanner.nextLine());
           }

           //Close the scanner
           scanner.close();

           System.out.println(informationString);

           //JSON simple library Setup with Maven is used to convert strings to JSON
           JSONParser parse = new JSONParser();
           JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

           //Get the first JSON object in the JSON array
           System.out.println(dataObject.get(0));

           JSONObject countryData = (JSONObject) dataObject.get(0);

           System.out.println(countryData.get("woeid"));
       }
        } catch (Exception e) {
            e.printStackTrace();
        }



      // con.setRequestProperty("X-Gismeteo-Token", "56b30cb255.3443075");
       //con.setDoOutput(true);



/*
       String url = "urlToConnect";
       HttpURLConnection httpUrlConnection = getURLConnection(url);

       Map<String, String> headers = new HashMap<>();

       headers.put("X-CSRF-Token", "fetch");
       headers.put("content-type", "application/json");

       setHeaders(httpUrlConnection, headers);
       httpUrlConnection.getContent();

 */

    }


}
