package com.example.echodesign.api;

import com.example.echodesign.MyApplication;
import com.example.echodesign.Post;
import com.example.echodesign.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public PostAPI() {

        retrofit = new Retrofit.Builder()
            .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Post>> call = webServiceAPI.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}