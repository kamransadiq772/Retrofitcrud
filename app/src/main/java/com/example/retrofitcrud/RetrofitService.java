package com.example.retrofitcrud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET("users")
    @Headers({"Accept:application/json","Content-Type:application/json"})
    Call<List<User>> getUserList();


    @GET("users")
    @Headers({"Accept:application/json","Content-Type:application/json"})
    Call<List<User>> searchUser(@Query("name") String searchtext);



    @GET("users/{id}")
    @Headers({"Accept:application/json","Content-Type:application/json"})
    Call<User> getUser(@Path("id") String id);



    @POST("users")
    @Headers({"Accept:application/json","Content-Type:application/json","Authorization: Bearer 7235c8f1d2ac1ecd45cb7c7bc847db01680f84a3cb9e316086d6c3d253a28009"})
    Call<UserResponse> createUser(@Body User params);



    @PATCH("users/{id}")
    @Headers({"Accept:application/json","Content-Type:application/json","Authorization: Bearer 7235c8f1d2ac1ecd45cb7c7bc847db01680f84a3cb9e316086d6c3d253a28009"})
    Call<UserResponse> updateUser(@Body User params, @Path("id") String id);



    @DELETE("users/{id}")
    @Headers({"Accept:application/json","Content-Type:application/json","Authorization: Bearer 7235c8f1d2ac1ecd45cb7c7bc847db01680f84a3cb9e316086d6c3d253a28009"})
    Call<UserResponse> deleteUser(@Path("id") String id);

}
//curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/users" -d '{"name":"Tenali Ramakrishna", "gender":"male", "email":"tenali.ramakrishna@15ce.com", "status":"active"}'