package com.looke.bakery.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.looke.bakery.R
import com.looke.bakery.model.Product
import com.looke.bakery.model.ProductWithTopping
import com.looke.bakery.rest.ApiClient
import com.looke.bakery.ui.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToppingFragment : Fragment() {

    private var columnCount = 1
    lateinit var products: ArrayList<Product>
    lateinit var productsTopping: ArrayList<ProductWithTopping>
    private lateinit var view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView

        products = ArrayList<Product>()
        productsTopping = ArrayList<ProductWithTopping>()
        fetchProducts()

        view.layoutManager = LinearLayoutManager(context)
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                ItemFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    private fun fetchProducts() {
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                products = response.body() as ArrayList<Product>
                products.mapIndexed {index, original ->

                    original.topping?.map {
                        productsTopping.add(
                                ProductWithTopping(
                                        it.id,
                                        it.type,
                                        original.id,
                                        original.type,
                                        original.name,
                                        original.ppu
                                )
                        )
                    }
                }
                view.adapter = ToppingsRecyclerViewAdapter(productsTopping)
                (view.adapter as ToppingsRecyclerViewAdapter).notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                //Toast.makeText(this, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                Log.e("Bakery Activity", t.localizedMessage)
            }
        })
    }
}