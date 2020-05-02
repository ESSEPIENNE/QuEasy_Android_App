package com.essepienne.mallin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NegozioAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Negozio> Negozi;

    public NegozioAdapter(Context context, ArrayList Negozi) {
        this.context = context;
        this.Negozi=Negozi;

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
        Negozio currentNegozio = getItem(position);
        TextView nome = convertView.findViewById(R.id.nameShop);
        ProgressBar pb = convertView.findViewById(R.id.progressBar);
        pb.setMax(currentNegozio.getMax_in_store()+currentNegozio.getMax_queue());
        pb.setProgress(currentNegozio.getCurrent_in_store());
        pb.setSecondaryProgress(currentNegozio.getCurrent_queue()+currentNegozio.getCurrent_in_store());

        ImageView logo = convertView.findViewById(R.id.ImmagineNegozio);
        Picasso.get()
                .load(currentNegozio.getImmagine())
                .resize(100, 100)
                .centerCrop()
                .into(logo);


        nome.setText(currentNegozio.getNome());

        return convertView;
    }
}
