package com.essepienne.mallin;

import org.json.JSONException;
import org.json.JSONObject;

public class Negozio {
    private String nome;
    private boolean disponibile;
    private String immagine;

    Negozio(JSONObject NegozioJson) throws JSONException {
        this.nome = NegozioJson.getString("name");
        this.disponibile = NegozioJson.getBoolean("disponibile");
        this.immagine=NegozioJson.getString("immagine");
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

}
