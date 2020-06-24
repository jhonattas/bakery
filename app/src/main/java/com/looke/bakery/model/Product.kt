package com.looke.bakery.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("ppu")
    @Expose
    var ppu: Double = 0.0
    @SerializedName("batters")
    @Expose
    var batters: Batters? = null
    @SerializedName("topping")
    @Expose
    var topping: List<Complement>? = null

    override fun toString(): String {
        return "Product(id=$id, type=$type, name=$name, ppu=$ppu, batters=$batters, toppings=$topping)"
    }



}