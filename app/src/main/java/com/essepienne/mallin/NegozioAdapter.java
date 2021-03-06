package com.essepienne.mallin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.random;

public class NegozioAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Negozio> Negozi;

    public NegozioAdapter(Context context, ArrayList Negozi) {
        this.context = context;
        this.Negozi = Negozi;
    }


    @Override
    public int getCount() {
        return Negozi.size();
    }

    @Override
    public Negozio getItem(int position) {
        return Negozi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.negozio, parent, false);
        }
        Animation animation;
        int i = ((int) (random() * 10)) % 2;
        if (i % 2 == 0) {
            animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_left);
        } else {
            animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_right);
        }
        //convertView.startAnimation(animation);
        Negozio currentNegozio = getItem(position);
        TextView nome = convertView.findViewById(R.id.nameShop);
        TextView PersoneInCoda = convertView.findViewById(R.id.PersoneInCoda);
        TextView PersoneDentroAlNegozio=convertView.findViewById(R.id.PersoneDentroAlNegozio);
        PersoneDentroAlNegozio.setText("Persone dentro al negozio: "+currentNegozio.getCurrent_in_store());
        PersoneInCoda.setText("Persone in coda: "+currentNegozio.getCurrent_queue());

        PersoneDentroAlNegozio.setVisibility(View.INVISIBLE);
        PersoneInCoda.setVisibility(View.INVISIBLE);
        Random rand = new Random();
        ProgressBar ProgressoCoda = convertView.findViewById(R.id.ProgressCoda);
        ProgressBar ProgressoNegozio = convertView.findViewById(R.id.ProgressNegozio);

        ProgressoCoda.setMax(currentNegozio.getMax_queue());
        ProgressoCoda.setProgress(currentNegozio.getCurrent_queue());
        //ProgressoCoda.setProgress(rand.nextInt(currentNegozio.getMax_queue()));
        ProgressoNegozio.setMax(currentNegozio.getMax_in_store());
        ProgressoNegozio.setProgress(currentNegozio.getCurrent_in_store());
        //ProgressoNegozio.setProgress(rand.nextInt(currentNegozio.getMax_in_store()));

        ImageView logo = convertView.findViewById(R.id.ImmagineNegozio);
        Picasso.get()
                .load(currentNegozio.getImmagine())
                .resize(500, 500)
                .centerCrop()
                .into(logo);


        nome.setText(currentNegozio.getNome());
        convertView.setElevation(1);

        return convertView;
    }
}
