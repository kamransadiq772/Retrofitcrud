package com.example.retrofitcrud;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class secondViewModel extends ViewModel {


    public MutableLiveData<UserResponse> createuser = new MutableLiveData<>();
    public MutableLiveData<UserResponse> getsecondobserverlist(){
        return createuser;
    }

    public MutableLiveData<User> recievedUser = new MutableLiveData<>();
    public MutableLiveData<User> getRecievedUser(){ return recievedUser; };


    void createUser(User user){

        RetrofitService retrofitService = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        retrofitService.createUser(user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()){
                    createuser.postValue(response.body());
                }else {
                    createuser.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                createuser.postValue(null);

            }
        });

    }


    public void getUser(String id){

        RetrofitService retrofitService = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        retrofitService.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    recievedUser.postValue(response.body());
                }else {
                    recievedUser.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                recievedUser.postValue(null);
            }
        });


    }

    public void updateUser(User user, String id){
        RetrofitService retrofitService = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        retrofitService.updateUser(user,id).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    Log.d("passed result", "onResponse: Successfully updated");
                }else {
                    Log.d("failed", "onResponse: in else failed");
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("failed", "onFailure: onFailure failed");
            }
        });
    }

    public void deleteUser(String id){
        RetrofitService retrofitService = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        retrofitService.deleteUser(id).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    Log.d("Success", "onResponse: Deleted success fully");
                }else {
                    Log.d("failed", "onResponse: failed in else");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("failed", "onFailure: failed in onFailure");
            }
        });
    }


}
