package com.example.mvpexample;

public class NetworkUtils {
    static ApiService service;
    public static ApiService getService(){
        if (service == null){
            service = Retrofitadapter.getInstance().create(ApiService.class);
        }
        return service;
    }
}
