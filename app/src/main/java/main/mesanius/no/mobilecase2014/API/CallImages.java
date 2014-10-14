package main.mesanius.no.mobilecase2014.API;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import main.mesanius.no.mobilecase2014.MainActivity;

/**
 * Created by Sondre on 14.10.2014.
 */
public class CallImages extends AsyncTask<Void,Void,Void>
{
    private Bitmap bitmap;
    //private ImageView imageView;
    private ProgressDialog progressDialog;
    private ArrayList<Bitmap> images;
    private int length;
    private Context context;

    public CallImages(Context context, int length) {
        this.context = context;
        this.length = length;
        images = new ArrayList<Bitmap>();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading images...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }


    @Override
    protected Void doInBackground(Void... params) {
        for(int i = 1; i <= length; i++) {
            try
            {
                bitmap = downloadBitmap(APIManager.apiURL + "images/get/img_" + i + ".png");
                images.add(bitmap);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        if(bitmap!=null)
        {
            ((MainActivity)context).saveImages(images);

        }
        else
        {
            Log.d("bitmap == null", "faaaakk");
        }

    }

    private Bitmap downloadBitmap(String getUrl) {
        try {
            URL url = new URL(getUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestProperty("accept", "image/png");
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("getBmpFromUrl error: ", e.getMessage().toString());
            return null;
        }

    }
}