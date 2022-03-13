package com.sd.laborator.services

import com.sd.laborator.interfaces.FilterLocationInterface
import com.sd.laborator.resources.BlackList
import org.springframework.stereotype.Service

@Service
class FilterLocationService (private val locationService:LocationSearchService ):FilterLocationInterface{
    lateinit var blackList:BlackList


    fun initBlackList(cities:BlackList)
    {
        blackList=cities
    }

    override fun filter(locationName: String): Int {
        if(blackList.getCities().contains(locationName))
            return -1
        return locationService.getLocationId(locationName)
    }

}