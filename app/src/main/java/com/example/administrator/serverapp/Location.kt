package com.example.administrator.serverapp

class Location {
    private var locationName: String? = null
    private var longitude: String? = null
    private var latitude: String? = null

    constructor()

    fun setLocationName(s: String) {
        this.locationName = s
    }

    fun setLongitude(s: String) {
        this.longitude = s
    }

    fun setLatitude(s: String) {
        this.latitude = s
    }
}
