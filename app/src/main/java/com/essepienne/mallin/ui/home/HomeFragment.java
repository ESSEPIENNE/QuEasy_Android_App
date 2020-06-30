package com.essepienne.mallin.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.essepienne.mallin.Negozio;
import com.essepienne.mallin.NegozioAdapter;
import com.essepienne.mallin.R;
import com.essepienne.mallin.Richieste.Get;

import org.json.JSONArray;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private CardView CurrentCard;
    private View movehere;
    private float startX;
    private float startY;
    private float moveHereX;
    private float moveHereY;
    private int DurationAnimation=300;
    private SwipeRefreshLayout swipeContainer ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);
        final Context ctx = getContext();
        GridView listaNegozi = root.findViewById(R.id.lista);
        movehere = root.findViewById(R.id.moveHere);

        swipeContainer = (SwipeRefreshLayout) root.findViewById(R.id.swipeContainer);

        root.setElevation(0f);
        root.setOnClickListener((click)->closeCard());

        listaNegozi.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                closeCard();
            }
        });
        AggiungiNegozi(ctx,listaNegozi);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AggiungiNegozi(ctx,listaNegozi);
            }
        });
        return root;
    }
public void AggiungiNegozi(Context ctx, GridView listaNegozi){
    Get.genericGetArray(ctx, "/stores", (response -> {
        try {
            ArrayList<Negozio> Negozi = new ArrayList<>();
            JSONArray NegoziJson = (JSONArray) response;
            for (int i = 0; i < NegoziJson.length(); i++)
                Negozi.add(new Negozio(NegoziJson.getJSONObject(i)));
            NegozioAdapter adapter = new NegozioAdapter(ctx, Negozi);
            listaNegozi.setAdapter(adapter);
            listaNegozi.setOnItemClickListener((parent, view, position, id) -> {
                if(view==CurrentCard)closeCard();
                else openCard(view);

            });
            swipeContainer.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }));
}
    public void closeCard(){
        if(CurrentCard!=null){
            CurrentCard.animate()
                    .setDuration(DurationAnimation)
                    .translationX(0)
                    .translationY(0)
                    .scaleX(1f)
                    .scaleY(1f)
                    .withLayer().start();

            View immagine = CurrentCard.findViewById(R.id.LayoutNegozioSopra);
            immagine.animate().setDuration(DurationAnimation)
                    .scaleX(1f)
                    .scaleY(1f)
                    .translationY(0f)
                    .withLayer()
                    .start();

            TextView PersoneInCoda = CurrentCard.findViewById(R.id.PersoneInCoda);
            TextView PersoneDentroAlNegozio=CurrentCard.findViewById(R.id.PersoneDentroAlNegozio);

            PersoneInCoda.setVisibility(View.INVISIBLE);
            PersoneDentroAlNegozio.setVisibility(View.INVISIBLE);

            CurrentCard.setElevation(1);
            CurrentCard=null;
        }
    }
    public void openCard(View view){
        closeCard();
        CurrentCard = (CardView) view;
        startX = CurrentCard.getX();
        startY = CurrentCard.getY();
        moveHereX = movehere.getX();
        moveHereY = movehere.getY();
        View immagine = view.findViewById(R.id.LayoutNegozioSopra);


        immagine.animate().setDuration(DurationAnimation)
                .scaleX(0.8f)
                .scaleY(0.8f)
                .translationY(-50f)
                .withLayer()
                .start();

        CurrentCard.animate()
                .setDuration(DurationAnimation)
                .scaleX(2f)
                .scaleY(2f)
                .translationX(moveHereX-startX)
                .translationY(moveHereY-startY)
                .withLayer()
                .start();

        CurrentCard.setOnClickListener((carta)->{
            closeCard();
            carta.setOnClickListener(this::openCard);
        });
        TextView PersoneInCoda = view.findViewById(R.id.PersoneInCoda);
        TextView PersoneDentroAlNegozio=view.findViewById(R.id.PersoneDentroAlNegozio);

        PersoneInCoda.setVisibility(View.VISIBLE);
        PersoneDentroAlNegozio.setVisibility(View.VISIBLE);
        CurrentCard.setElevation(10);

    }
}

