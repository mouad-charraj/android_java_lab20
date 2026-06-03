package com.example.lab20;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ContactApi_mouad {

    @POST("insertContact.php")
    Call<ApiResponse_mouad> insertContact_mouad(@Body Contact_mouad contact_mouad);

    @GET("getAllContacts.php")
    Call<List<Contact_mouad>> getAllContacts_mouad();

    @GET("searchContact.php")
    Call<List<Contact_mouad>> searchContacts_mouad(@Query("keyword") String keyword_mouad);
}
