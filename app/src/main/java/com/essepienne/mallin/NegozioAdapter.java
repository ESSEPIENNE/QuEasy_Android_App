package com.essepienne.mallin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ImageView Disponibilita = convertView.findViewById(R.id.disponibilita);
        if(currentNegozio.getDisponibilita()){
            Disponibilita.setImageResource(R.drawable.ic_check_black_24dp);
        }
        else{
            Disponibilita.setImageResource(R.drawable.ic_cancel_black_24dp);
        }
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
