package com.essepienne.mallin;

import android.content.Context;
import android.content.SharedPreferences;

import com.essepienne.mallin.Richieste.Post;

import org.json.JSONException;
import org.json.JSONObject;

public class CodeUtils {

    public static void CreaCodice(Context ctx){
        Post.genericPostObject(ctx, "/codes", (Risposta) -> {
            try {
                SharedPreferences pref = ctx.getSharedPreferences("codice", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("codice", ((JSONObject) Risposta).getString("code"));
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public static String GetCodice(Context ctx){
        SharedPreferences pref = ctx.getSharedPreferences("codice", Context.MODE_PRIVATE);
        return pref.getString("codice",null);
    }
}
