package com.e.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var txtTown: TextView;
    lateinit var txtTemperature: TextView;
    lateinit var btnGetWeather: Button;
    lateinit var btnCopyWeather: Button;

    data class Weather(val town: String, val temperature: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.getWeather();
        Repository.printStrings("Привет", "Пока", "Еще один")
        val weather: Weather = Weather("Москва", 15);
        txtTown = findViewById(R.id.txtTown);
        txtTemperature = findViewById(R.id.txtTemprature);
        btnGetWeather = findViewById(R.id.btnGetWeather);
        btnCopyWeather = findViewById(R.id.btnCopyWeather);
        btnGetWeather.setOnClickListener {
            txtTown.setText(weather.town);
            txtTemperature.setText(weather.temperature.toString());
        }
        btnCopyWeather.setOnClickListener {
            val copyWeather = weather.copy(town = "Питер");
            txtTown.setText(copyWeather.town);
            txtTemperature.setText(copyWeather.temperature.toString());
        }
    }
}