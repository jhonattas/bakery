package com.looke.bakery.ui.interfaces

import com.looke.bakery.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("teste.json")
    fun allProducts(): Call<List<Product>>

}