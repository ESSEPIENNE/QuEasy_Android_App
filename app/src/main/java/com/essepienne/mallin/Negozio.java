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
        this.disponibile = true;
        this.id = NegozioJson.getString("_id");
        this.immagine = Config.getInstance().url + "/stores/" + this.id + "/logo";
    }

    public String getNome() {
        return nome;
    }

    public boolean getDisponibilita() {
        return this.disponibile;
    }

    public String getImmagine() {
        return this.immagine;
    }

    public String getId() {
        return id;
    }
}
