package com.example.administrator.serverapp

class Location {
    private var locationName: String? = null
    private var longitude: String? = null
    private var latitude: String? = null

    constructor(locationName: String, longitude: String, latitude: String) {
        this.locationName = locationName
        this.longitude = longitude
        this.latitude = latitude
    }

    constructor()

    fun getLocationName(): String? {
        return this.locationName
    }

    fun setLocationName(s : String) {
        this.locationName = s
    }

    fun getLongitude(): String? {
        return this.longitude
    }

    fun setLongitude(s : String) {
        this.longitude = s
    }

    fun getLatitude(): String? {
        return this.latitude
    }

    fun setLatitude(s : String) {
        this.latitude = s
    }
}
