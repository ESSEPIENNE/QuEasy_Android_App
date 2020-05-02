package com.essepienne.mallin.Richieste;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.essepienne.mallin.Config;
import com.essepienne.mallin.InterfacciaRichieste;

public class Post {

    public static void genericPostObject(final Context ctx, final String url, final InterfacciaRichieste f) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Config.getInstance().url + url, null,
                response -> {
                    f.apply(response);
                },
                error -> error.printStackTrace());
        queue.add(request);
    }
}
