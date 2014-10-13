package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import main.mesanius.no.mobilecase2014.Menu.MenuItem;

/**
 * Created by NegatioN on 13.10.2014.
 */
public class APIManager {

    public final static String INTENT_MESSAGE = "intentmessagefinal";

    public final static String apiURL = "http://localhost:8080/rest/menu/";
    private Context context;


    //returns the raw JSON-string
    public static String fetchJSON(String[] strings){
        String urlString;
        //if there are no extra string-inputs, we get the full menu.
        if(!strings[0].equals(""))
            urlString = apiURL + strings[0]; // url to call
        else
            urlString = apiURL;

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



    //converts our inputstream to a JSON-string
    private static String convertStreamToString(InputStream is){
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    //gets all the menuitems from our database and returns them in an arraylist.
    public static ArrayList<MenuItem> getMenuFromString(String in){
        int menulength = 0;
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        try {
            JSONArray menuArray = new JSONArray(in);
            menulength = menuArray.length();
            int i = 0;
            while(i < menulength){
                //add menuitem to menu from jsonobject
                MenuItem mi = getMenuItemFromJSON(menuArray.getJSONObject(i));
                menu.add(mi);
                Log.d("menuArray.add", "Added menuitem: " + mi.getName());
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

    public static MenuItem getMenuItemFromJSON(JSONObject reader){
        try {
            String name = reader.getString("name");
            int price = Integer.parseInt(reader.getString("price"));
            int id = Integer.parseInt(reader.getString("id"));

            return new MenuItem(name, price, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
