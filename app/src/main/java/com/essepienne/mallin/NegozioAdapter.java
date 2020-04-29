package com.essepienne.mallin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NegozioAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Negozio> Negozi; //data source of the list adapter
    NegozioAdapter(Context context){
        this.context=context;
        Negozi=new ArrayList<Negozio>();
        for (int i=0;i<20;i++){
            Negozi.add(new Negozio(""+i));
        }

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
        Negozio currentNegozio=getItem(position);
        TextView nome= convertView.findViewById(R.id.nameShop);
        nome.setText(currentNegozio.getNome());
        return convertView;
    }
}
