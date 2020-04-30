package com.essepienne.mallin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context ctx = this;

        queue = Volley.newRequestQueue(this);
        Config.getInstance().url="http://192.168.1.223:6969";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Config.getInstance().url+"/stores", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Negozio>  Negozi = new ArrayList<Negozio>();
                        try {
                            for (int i=0;i<response.length();i++) Negozi.add(new Negozio(response.getJSONObject(i)));
                            ListView l = findViewById(R.id.lista);
                            l.setAdapter(new NegozioAdapter(ctx,Negozi));

                        }
                        catch(Exception e){

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();

            }
        });


        queue.add(request);


    }
}
