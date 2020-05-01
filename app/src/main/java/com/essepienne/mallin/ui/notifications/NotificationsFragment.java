package com.essepienne.mallin.ui.notifications;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.essepienne.mallin.Config;
import com.essepienne.mallin.QrGenerator;
import com.essepienne.mallin.R;
import com.essepienne.mallin.Richieste.Get;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final Context ctx = getActivity();
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final ImageView qr = root.findViewById(R.id.qr);
        Display display = container.getDisplay();
        Point size = new Point();
        display.getSize(size);
        Get.genericGetArray(ctx , "/codes",(Risposta)->{
            try {
                JSONObject AllDay = ((JSONArray)Risposta).getJSONObject(0);
                JSONObject OneTime = ((JSONArray)Risposta).getJSONObject(1);
                QrGenerator q = new QrGenerator(AllDay.getString("code"),size.y/2);
                qr.setImageBitmap(q.QRGenerator());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });




        return root;
    }
}
