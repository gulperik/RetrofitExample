package com.example.apideneme;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    interface RequestUser{
        @GET("/api/users/{uid}")
       Call<UserData>  getUser(@Path("uid") String uid);

    }

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create()).build();


         RequestUser requestUser = retrofit.create(RequestUser.class);

         requestUser.getUser("3").enqueue(new Callback<UserData>() {
             @Override
             public void onResponse(Call<UserData> call, Response<UserData> response) {

                 textView.setText(response.body().data.first_name);

             }

             @Override
             public void onFailure(Call<UserData> call, Throwable t) {
               textView.setText(t.getMessage());
             }
         });


    }
}