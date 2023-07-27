package mobile.computing.apiexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mobile.computing.apiexercise.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherApi = RetrofitClient.retrofit.create(WeatherApi::class.java)

        binding.btnGetWeather.setOnClickListener {
            callApi(weatherApi)
        }
    }

    private fun callApi(weatherApi: WeatherApi){
        val apiKey = "b40e9adf52ff46ce883143047232707"
        val city = binding.etCity.text.toString()
        val call = weatherApi.getWeather(city, apiKey)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    binding.tvCity.text = weatherResponse?.coord?.name
                    binding.tvLonLat.text = "${weatherResponse?.coord?.lat}/${weatherResponse?.coord?.lon}"
                    binding.tvTemp.text = "${weatherResponse?.weather?.temp.toString()} C"

                } else {
                    Log.d("MYTAG", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle network failures here
                Log.d("MYTAG", "${t.message}")
            }
        })
    }
}