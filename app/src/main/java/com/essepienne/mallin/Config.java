package com.essepienne.mallin;

public class Config {

    private static Config single_instance = null;


    public String url;

    private Config() {
    }

    public static Config getInstance() {
        if (single_instance == null)
            single_instance = new Config();

        return single_instance;
    }
}
