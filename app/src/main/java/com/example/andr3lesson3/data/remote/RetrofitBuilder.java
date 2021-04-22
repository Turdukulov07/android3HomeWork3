package com.example.andr3lesson3.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    // конструктор нужен что бы в других классах не могли создать новый RetrofitBuilder
    private RetrofitBuilder (){
    }

    private static MockerApi instance;

    public static MockerApi getInstance() {
        if (instance == null) {
            instance = createApi();
        }
        return instance;
    }

    private static MockerApi createApi() {
        return new Retrofit
                .Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MockerApi.class);
    }

}
