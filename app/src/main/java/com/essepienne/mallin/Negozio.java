package com.essepienne.mallin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Negozio implements Serializable {
    private String nome;
    private String immagine;
    private String id;
    private int max_queue;
    private int max_in_store;
    private int current_queue;
    private int current_in_store;



    public Negozio(JSONObject NegozioJson) throws JSONException {
        this.nome = NegozioJson.getString("name");
        this.id = NegozioJson.getString("_id");
        this.immagine = Config.getInstance().url + "/stores/" + this.id + "/logo";
        this.max_in_store= NegozioJson.getInt("max_in_store");
        this.max_queue=NegozioJson.getInt("max_queue");
        current_queue=40;
        current_in_store=10;
    }

    public String getNome() {
        return nome;
    }


    public String getImmagine() {
        return this.immagine;
    }

    public String getId() {
        return id;
    }


    public int getMax_queue() {
        return max_queue;
    }

    public int getMax_in_store() {
        return max_in_store;
    }
    public int getCurrent_queue() {
        return current_queue;
    }

    public int getCurrent_in_store() {
        return current_in_store;
    }


}
