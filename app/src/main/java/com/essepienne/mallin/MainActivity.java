package com.essepienne.mallin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.essepienne.mallin.Richieste.Get;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context ctx = this;
        Config.getInstance().url = "http://192.168.1.223:6969";

        Get.genericGet(ctx,"/stores", (response -> {
            try {
                ArrayList<Negozio> Negozi = new ArrayList<>();
                JSONArray NegoziJson = (JSONArray) response;

                for (int i = 0; i < NegoziJson.length(); i++)
                    Negozi.add(new Negozio(NegoziJson.getJSONObject(i)));


                ListView l = findViewById(R.id.lista);
                l.setAdapter(new NegozioAdapter(ctx, Negozi));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));


    }
}
