package main.mesanius.no.mobilecase2014.API;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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
import main.mesanius.no.mobilecase2014.Order.Order;
import main.mesanius.no.mobilecase2014.Order.OrderItem;

/**
 * Created by NegatioN on 13.10.2014.
 */
public class APIManager {

    public final static String apiURL = "http://localhost:8080/rest/";
    private Context context;


    //returns the raw JSON-string
    public static String fetchJSON(String string){
        //apiUrl in addition to spesific path such as order/ menu/ or menu/item
        String urlString = apiURL +string; // url to call

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

    //returns the raw JSON-string
    public static String fetchJSON(CallMenu callMenu, String string){
        //apiUrl in addition to spesific path such as order/ menu/ or menu/item
        String urlString = apiURL +string; // url to call

        Log.d("ApiUrl", urlString);

        InputStream in = null;

        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            callMenu.sendProgress(10);
            urlConnection.setReadTimeout(100000);
            urlConnection.setConnectTimeout(500000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            callMenu.sendProgress(25);
            urlConnection.connect();

            in = urlConnection.getInputStream();
            callMenu.sendProgress(50);
            String data = convertStreamToString(in);
            callMenu.sendProgress(70);
            Log.d("JSONdata.raw", data);
            in.close();
            callMenu.sendProgress(100);
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

    public static String sendCompleteOrderJSON(Order order){
        InputStream in = null;
        String result = "";

        String urlString = apiURL + "completeorder";

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlString);

            String jsonData = "";


            JSONObject jsonObject = new JSONObject();

            JSONArray orderItems = new JSONArray();

            for(OrderItem item : order.getOrderItems()){
                JSONObject itemObject = new JSONObject();
                itemObject.accumulate("item", item.getItemId());
                itemObject.accumulate("quantity", item.getQuantity());

                orderItems.put(itemObject);
            }
            jsonObject.accumulate("items", orderItems);

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


    public static boolean deleteOrder(int orderId){
        String urlString = apiURL + "order/" + orderId;
        InputStream in = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(urlString);
            HttpResponse httpResponse = httpClient.execute(httpDelete);

            in = httpResponse.getEntity().getContent();
            if(in != null){
                Log.d("httpdelete", convertPostStreamToString(in));
                in.close();
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteCompleteOrder(int orderId){
        String urlString = apiURL + "completeorder/" + orderId;
        InputStream in = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(urlString);
            HttpResponse httpResponse = httpClient.execute(httpDelete);

            in = httpResponse.getEntity().getContent();
            if(in != null){
                Log.d("httpdelete", convertPostStreamToString(in));
                in.close();
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public static Order getOrderFromString(String JSONstring){
        Order order = new Order();
        try{

            JSONObject reader = new JSONObject(JSONstring);
            JSONArray itemArray = reader.getJSONArray("items");
            int orderId = Integer.parseInt(reader.getString("orderId"));


            for(int i = 0; i < itemArray.length();i++){
                JSONObject orderJson = itemArray.getJSONObject(i);

                OrderItem orderItem = new OrderItem(orderJson.getInt("item"), orderJson.getInt("quantity"));
                order.addOrderItem(orderItem);
            }

        }catch(Exception e){
            e.printStackTrace();

        }


        return order;
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

    public static OrderItem getOrderItemFromJSON(JSONObject reader){
        try{
            int id = Integer.parseInt(reader.getString("item"));
            int quantity = Integer.parseInt(reader.getString("quantity"));

            return new OrderItem(id, quantity);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
