package com.shanker.memsshareing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.ErrorListener;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(ImageView)findViewById(R.id.imageView);
        queue = Volley.newRequestQueue(this);
        loadImage();
    }

    void loadImage(){
        String url ="https://meme-api.herokuapp.com/gimme";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageUrl = null;
                            imageUrl = response.getString("url");
                            Log.i("msg","image url "+imageUrl);
                            Glide.with(MainActivity.this).load(imageUrl).into(imageView);
                        } catch (JSONException e) {
                            Log.i("msg",e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Log.i("msg",error.getMessage());
                    }
                });

        queue.add(jsonObjectRequest);
    }





    public void shareMeme(View view) {

    }

    public void nextMeme(View view) {
        loadImage();
    }
}