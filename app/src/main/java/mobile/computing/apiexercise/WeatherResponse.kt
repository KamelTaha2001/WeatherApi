package mobile.computing.apiexercise

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val coord: Location,
    @SerializedName("current") val weather: Weather
)

data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class Weather(
    @SerializedName("temp_c") val temp: Double,
)