package com.example.retrofitcrud;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewModel extends ViewModel {


    public MutableLiveData<List<User>> userList = new MutableLiveData<>();
    public MutableLiveData<List<User>> getobserverlist(){
        return userList;
    }


    void getUserList(){

        RetrofitService retrofitService = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        retrofitService.getUserList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    userList.postValue(response.body());
                }
            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                userList.postValue(null);

            }
        });

    }


    void searchUser(String searchText){

        RetrofitService retrofitService = RetrofitInstance.getRetrofit().create(RetrofitService.class);
        retrofitService.searchUser(searchText).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    userList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                userList.postValue(null);

            }
        });

    }


}
