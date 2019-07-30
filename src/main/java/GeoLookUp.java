import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;

public class GeoLookUp {
    static ArrayList<String> getGeoFromIP(String ip){
        String url = "http://api.ipstack.com/"+ip+"?access_key="+secret.getApiKey();
        HttpGet get = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse resp = null;
        try{
            resp = httpClient.execute(get);
        }catch (IOException IOE){IOE.printStackTrace(); }
        final int statusCode = resp.getStatusLine().getStatusCode();
        String getResult;
        if(statusCode != HttpStatus.SC_OK){
            System.err.println(resp.getStatusLine().getStatusCode());
            System.err.println("Error connecting to salesforce.com");
        }
        JSONObject json;
        ArrayList<String> geo = new ArrayList<>();
        try {
            getResult = EntityUtils.toString(resp.getEntity());
            json = (JSONObject) new JSONTokener(getResult).nextValue();
            geo.add(json.getString("country_name"));
            geo.add(json.getString("city"));
            geo.add(json.getString("latitude"));
            geo.add(json.getString("longitude"));

        }catch (IOException IOE) { IOE.printStackTrace(); }
        return geo;
    }
}
