package com.essepienne.mallin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class NegozioActivity extends AppCompatActivity {
    private ImageView LogoNegozio;
    private TextView nomeNegozio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negozio);
        LogoNegozio=findViewById(R.id.logoNegozio);
        nomeNegozio=findViewById(R.id.nomeNegozio);

        Intent intent = getIntent();
        Negozio negozio = (Negozio)intent.getSerializableExtra("idNegozio");
        Picasso.get()
                .load(negozio.getImmagine())
                .resize(200, 200)
                .centerCrop()
                .into(LogoNegozio);
        nomeNegozio.setText(negozio.getNome());

    }
}
