package com.looke.bakery.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.looke.bakery.R
import com.looke.bakery.model.Product
import com.looke.bakery.rest.ApiClient
import com.looke.bakery.ui.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BakeryActivity : AppCompatActivity() {

    lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bakery)
        setSupportActionBar(findViewById(R.id.toolbar))

        products = ArrayList<Product>()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            fetchProducts()
        }

    }

    fun fetchProducts() {
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                var resposta = response.body()
                Log.e("resposta", resposta.toString())
                Toast.makeText(applicationContext, "encontrei " + resposta.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                //Toast.makeText(this, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                Log.e("Bakery Activity", t.localizedMessage)
            }
        })
    }
}