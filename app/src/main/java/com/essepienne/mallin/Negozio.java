package com.essepienne.mallin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Negozio implements Serializable {
    private String nome;
    private boolean disponibile;
    private String immagine;
    private String id;

    public Negozio(JSONObject NegozioJson) throws JSONException {
        this.nome = NegozioJson.getString("name");
        this.disponibile = NegozioJson.getBoolean("disponibile");
        this.immagine=NegozioJson.getString("immagine");
        this.id= NegozioJson.getString("id");
    }

    public String getNome() {
        return nome;
    }

    public boolean getDisponibilita() {
        return this.disponibile;
    }
    public String getImmagine(){
        return this.immagine;
    }

    public String getId() {
        return id;
    }
}
