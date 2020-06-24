package com.looke.bakery.model

data class ProductWithTopping(

    var toppingId: String? = null,
    var toppingContent: String? = null,
    var originalId: String? = null,
    var originalType: String? = null,
    var originalName: String? = null,
    var originalPpu: Double? = null
)

