package com.essepienne.mallin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.essepienne.mallin.Config;
import com.essepienne.mallin.Negozio;
import com.essepienne.mallin.NegozioActivity;
import com.essepienne.mallin.NegozioAdapter;
import com.essepienne.mallin.R;
import com.essepienne.mallin.Richieste.Get;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);
        final Context ctx = getContext();

        ListView listaNegozi = root.findViewById(R.id.lista);


        Get.genericGetArray(ctx, "/stores", (response -> {
            try {
                ArrayList<Negozio> Negozi = new ArrayList<>();
                JSONArray NegoziJson = (JSONArray) response;

                for (int i = 0; i < NegoziJson.length(); i++)
                    Negozi.add(new Negozio(NegoziJson.getJSONObject(i)));
                NegozioAdapter adapter = new NegozioAdapter(ctx, Negozi);
                listaNegozi.setAdapter(adapter);


                listaNegozi.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(ctx, NegozioActivity.class);
                    intent.putExtra("idNegozio", adapter.getItem(position));
                    startActivity(intent);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        return root;
    }
}
