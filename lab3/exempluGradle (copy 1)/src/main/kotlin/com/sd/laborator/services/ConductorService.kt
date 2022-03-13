package com.sd.laborator.services

import com.sd.laborator.interfaces.ConductorInterface
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.resources.BlackList
import org.springframework.stereotype.Service

@Service
class ConductorService(private val weatherForecastService: WeatherForecastService, private val filterLocation:FilterLocationService):ConductorInterface{
    override fun getData(locationName: String): WeatherForecastData? {
        filterLocation.blackList= BlackList()

        val locationId=filterLocation.filter(locationName)

        if(locationId==-1)
            return null

        return weatherForecastService.getForecastData(locationId)

    }
}