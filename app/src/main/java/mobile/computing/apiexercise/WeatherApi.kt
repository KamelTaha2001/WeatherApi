package mobile.computing.apiexercise

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    fun getWeather(
        @Query("q") city: String,
        @Query("key") apiKey: String
    ) : Call<WeatherResponse>
}