package com.sd.laborator.interfaces

import com.sd.laborator.pojo.WeatherForecastData

interface ConductorInterface {
    fun getData(locationName:String): WeatherForecastData?
}