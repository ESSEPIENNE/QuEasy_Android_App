package com.essepienne.mallin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.essepienne.mallin.Config;
import com.essepienne.mallin.Negozio;
import com.essepienne.mallin.NegozioActivity;
import com.essepienne.mallin.NegozioAdapter;
import com.essepienne.mallin.R;
import com.essepienne.mallin.Richieste.Get;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    CardView CurrentCard;
    float startX;
    float startY;
    float moveHereX;
    float moveHereY;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);
        final Context ctx = getContext();
        GridView listaNegozi = root.findViewById(R.id.lista);
        View movehere = root.findViewById(R.id.moveHere);
        root.setElevation(0f);
        root.setOnClickListener((click)->{
            CurrentCard.animate()
                    .setDuration(300)
                    .scaleX(1)
                    .scaleY(1)
                    .withLayer();
        });

        listaNegozi.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                closeCard();
            }
        });

//        BottomNavigationView navigationView = root.findViewById(R.id.nav_view);
//        navigationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        Get.genericGetArray(ctx, "/stores", (response -> {
            try {
                ArrayList<Negozio> Negozi = new ArrayList<>();
                JSONArray NegoziJson = (JSONArray) response;
                for (int k = 0; k < 50; k++)
                    for (int i = 0; i < NegoziJson.length(); i++)
                        Negozi.add(new Negozio(NegoziJson.getJSONObject(i)));
                NegozioAdapter adapter = new NegozioAdapter(ctx, Negozi);
                listaNegozi.setAdapter(adapter);


                listaNegozi.setOnItemClickListener((parent, view, position, id) -> {
                    closeCard();
                    CurrentCard = (CardView) view;
                    CurrentCard.setElevation(6f);
                    //c.bringToFront();
                     startX = CurrentCard.getX();
                     startY = CurrentCard.getY();
                     moveHereX = movehere.getX();
                     moveHereY = movehere.getY();
                    if (moveHereX == startX) {
//                        c.animate().withLayer()
//                                .rotationY(90)
//                                .setDuration(300)
//                                .scaleX(2/3f)
//                                .scaleY(2/3f)
//                                .withEndAction(
//                                        new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                c.setRotationY(-90);
//                                                c.animate().withLayer()
//                                                        .rotationY(0)
//                                                        .setDuration(300)
//                                                        .start();
//                                            }
//                                        }
//                                ).start();
                    } else {
                        TranslateAnimation animation = new TranslateAnimation(0, moveHereX - startX, 0, moveHereY - startY);
                        animation.setRepeatMode(0);
                        animation.setDuration(1000);
                        animation.setFillAfter(true);

                        CurrentCard.animate()
                                .setDuration(300)
                                .scaleX(1.5f)
                                .scaleY(1.5f)
                                .withLayer();

                        CurrentCard.startAnimation(animation);
                    }
//
//                    Intent intent = new Intent(ctx, NegozioActivity.class);
//                    intent.putExtra("idNegozio", adapter.getItem(position));
//                    startActivity(intent);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        return root;
    }

    public void closeCard(){
        if(CurrentCard!=null){

            TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 0);
            animation.setRepeatMode(0);
            animation.setDuration(1000);
            animation.setFillAfter(true);

            CurrentCard.animate()
                    .setDuration(300)
                    .scaleX(1f)
                    .scaleY(1f)
                    .withLayer();
            CurrentCard.startAnimation(animation);
            CurrentCard=null;
        }
    }
}

