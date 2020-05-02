package com.essepienne.mallin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.AttributeSet;
import android.view.View;

import com.essepienne.mallin.Richieste.Get;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class QueasyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Get.genericGetArray(getApplicationContext(), "/codes", (Risposta) -> {
            try {
                JSONObject AllDay = ((JSONArray) Risposta).getJSONObject(0);
                JSONObject OneTime = ((JSONArray) Risposta).getJSONObject(1);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("codice", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("codice", AllDay.getString("code"));
                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });;
        setContentView(R.layout.activity_queasy);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}
