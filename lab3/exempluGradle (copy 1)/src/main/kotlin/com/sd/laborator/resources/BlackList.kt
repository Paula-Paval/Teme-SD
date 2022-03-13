package com.sd.laborator.resources

class BlackList {
    val cities: List<String> = listOf("Mosul", "Erbil", "Amarah")

    @JvmName("getCities1")
    fun getCities():List<String>
    {
        return cities
    }
}