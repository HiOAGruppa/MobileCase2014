package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.mesanius.no.mobilecase2014.Menu.MenuItem;
import main.mesanius.no.mobilecase2014.Menu.MenuListItem;
import main.mesanius.no.mobilecase2014.Order.OrderItem;

/**
 * Created by NegatioN on 13.10.2014.
 */
public class APIManager {

    public final static String INTENT_MESSAGE = "intentmessagefinal";

    public final static String apiURL = "http://localhost:8080/rest/";
    private Context context;


    //returns the raw JSON-string
    public static String fetchJSON(String[] strings){
        String urlString;
        //if there are no extra string-inputs, we get the full menu.
        if(!strings[0].equals(""))
            urlString = apiURL + "menu/"+strings[0]; // url to call
        else
            urlString = apiURL + "menu/";

        Log.d("ApiUrl", urlString);

        InputStream in = null;

        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setReadTimeout(100000);
            urlConnection.setConnectTimeout(500000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            urlConnection.connect();

            in = urlConnection.getInputStream();
            String data = convertStreamToString(in);

            Log.d("JSONdata.raw", data);
            in.close();

            return data;
        }catch(Exception e){
            Log.d("GetJson", "error");
            e.printStackTrace();
        }

        return null;
    }


    //lets us send an order to create an order in api-db
    public static String sendJSONOrder(OrderItem order){
        InputStream in = null;
        String result = "";

        String urlString = apiURL + "order";

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlString);

            String jsonData = "";

            //create JSONobject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("quantity", order.getQuantity());
            jsonObject.accumulate("item", order.getItemId());

            jsonData = jsonObject.toString();

            Log.d("HttpPost.Jsondata", jsonData);

            StringEntity se = new StringEntity(jsonData);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpPost);

            in = httpResponse.getEntity().getContent();
            if(in != null){
                result = convertPostStreamToString(in);
                Log.d("httppost.result", result);
                //result = convertStreamToString(in);
            }else{
                result = "Post did not work";
            }
            in.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    //converts our inputstream to a JSON-string
    private static String convertStreamToString(InputStream is){
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private static String convertPostStreamToString(InputStream in)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        StringBuilder sb = new StringBuilder();

        while((line = br.readLine()) != null)
            sb.append(line);

        return sb.toString();
    }

    //gets all the menuitems from our database and returns them in an arraylist.
    public static List<MenuListItem> getMenuFromString(String in){
        int menulength = 0;
        ArrayList<MenuListItem> menu = new ArrayList<MenuListItem>();
        try {
            JSONArray menuArray = new JSONArray(in);
            menulength = menuArray.length();
            int i = 0;
            while(i < menulength){
                //add menuitem to menu from jsonobject
                MenuListItem mi = getMenuItemFromJSON(menuArray.getJSONObject(i));
                menu.add(mi);
                Log.d("menuArray.add", "Added menuitem: " + mi.getTitle());
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        return menu;
    }

    public static MenuItem getMenuItemFromString(String in){
        try{
            JSONObject reader = new JSONObject(in);
            String description = reader.getString("description");
            String name = reader.getString("name");
            int price = Integer.parseInt(reader.getString("price"));
            int id = Integer.parseInt(reader.getString("id"));

            return new MenuItem(name,description,price,id);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static MenuListItem getMenuItemFromJSON(JSONObject reader){
        try {
            String name = reader.getString("name");
            int price = Integer.parseInt(reader.getString("price"));
            int id = Integer.parseInt(reader.getString("id"));

            return new MenuListItem(id, price, name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
