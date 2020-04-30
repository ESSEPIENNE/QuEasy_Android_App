package com.essepienne.mallin.Richieste;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.essepienne.mallin.Config;
import com.essepienne.mallin.InterfacciaRichieste;
import com.essepienne.mallin.Negozio;

import java.util.ArrayList;
import java.util.function.Function;

public class Get {
    public static void genericGet(final Context ctx,final String url, final InterfacciaRichieste f) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Config.getInstance().url + url, null,
                response -> {
                    f.apply(response);
                },
                error -> error.printStackTrace());
        queue.add(request);
    }


    public static void getNegozi(final Context ctx, final Function f) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Config.getInstance().url + "/stores", null,
                response -> {
                    ArrayList<Negozio> Negozi = new ArrayList<Negozio>();
                    try {
                        for (int i = 0; i < response.length(); i++)
                            Negozi.add(new Negozio(response.getJSONObject(i)));
                        f.apply(Negozi);
                    } catch (Exception e) {
                    }
                },
                error -> error.printStackTrace());
        queue.add(request);
    }
}

