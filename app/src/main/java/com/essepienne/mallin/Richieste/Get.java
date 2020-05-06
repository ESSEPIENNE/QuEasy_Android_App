package com.essepienne.mallin.Richieste;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.essepienne.mallin.Config;


public class Get {
    public static void genericGetArray(final Context ctx,final String url, final InterfacciaRichieste f) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String finalUrl=Config.getInstance().url + url;


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, finalUrl, null,
                response -> {
                    f.apply(response);
                },
                error -> error.printStackTrace());
        queue.add(request);
    }

    public static void genericGetObject(final Context ctx,final String url, final InterfacciaRichieste f) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.getInstance().url + url, null,
                response -> {
                    f.apply(response);
                },
                error -> error.printStackTrace());
        queue.add(request);
    }
}

