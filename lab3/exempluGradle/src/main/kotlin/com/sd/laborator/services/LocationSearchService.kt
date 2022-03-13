package com.sd.laborator.services


import com.sd.laborator.interfaces.LocationSearchInterface
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Service
class LocationSearchService: LocationSearchInterface {
    override fun getLocationId(locationName: String): Int {
      val encodedLocationName= URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString())

      val locationSearchURL= URL("https://www.metaweather.com/api/location/search/?query=$encodedLocationName")

       val rawResponse:String=locationSearchURL.readText()

       val responseRootObject= JSONObject("{\"data\":${rawResponse}}")

        val responseContentObject=responseRootObject.getJSONArray("data").takeUnless{it.isEmpty}?.getJSONObject(0)
        return  responseContentObject?.getInt("woeid")?:-1
    }
}