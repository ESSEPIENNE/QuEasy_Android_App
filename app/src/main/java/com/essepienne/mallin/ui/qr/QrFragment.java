package com.essepienne.mallin.ui.qr;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.essepienne.mallin.QrGenerator;
import com.essepienne.mallin.R;

public class QrFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final Context ctx = getActivity();
        View root = inflater.inflate(R.layout.fragment_qr, container, false);
        final ImageView qr = root.findViewById(R.id.qr);
        TextView codice = root.findViewById(R.id.codiceText);
        Display display = container.getDisplay();
        Point size = new Point();
        display.getSize(size);
        assert ctx != null;
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("codice", Context.MODE_PRIVATE);
        String cod = sharedPreferences.getString("codice", "ciaomore");
        QrGenerator q = new QrGenerator(cod, size.y / 2);
        qr.setImageBitmap(q.QRGenerator());
        codice.setText(cod);
        return root;
    }
}
