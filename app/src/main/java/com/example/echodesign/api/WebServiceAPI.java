package com.example.echodesign.api;

import com.example.echodesign.Post;
import com.example.echodesign.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @GET("api/posts")
    Call<List<Post>> getPosts();

    @POST("api/posts")
    Call<Void> createPost(@Body Post post);

    @DELETE("api/posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    @GET("Users")
    Call<List<User>> getUsers();

    @POST("Users")
    Call<Void> createUser(@Query("userId") String userId, @Query("userName") String userName, @Query("password") String password, @Query("img") String img);

    @GET("Users/{userId}")
    Call<User> getUser(@Path("userId") String userId);
}
