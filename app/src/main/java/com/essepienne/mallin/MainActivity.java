package com.essepienne.mallin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.essepienne.mallin.Richieste.Get;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context ctx = this;
        Config.getInstance().url = "http://ross.ddns.net:6969";
        ListView listaNegozi = findViewById(R.id.lista);


        Get.genericGet(ctx,"/stores", (response -> {
            try {
                ArrayList<Negozio> Negozi = new ArrayList<>();
                JSONArray NegoziJson = (JSONArray) response;

                for (int i = 0; i < NegoziJson.length(); i++)
                    Negozi.add(new Negozio(NegoziJson.getJSONObject(i)));
                NegozioAdapter adapter =new NegozioAdapter(ctx, Negozi);
                listaNegozi.setAdapter(adapter);


                listaNegozi.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(ctx,NegozioActivity.class);
                    intent.putExtra("idNegozio",adapter.getItem(position));
                    startActivity(intent);



                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));



    }
}
