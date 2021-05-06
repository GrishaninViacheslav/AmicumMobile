package com.e.weather

object Repository {
//    private val weatherList: List<Weather>
//
//    init {
//        weatherList = listOf(Weather("Moscow", 15), Weather());
//    }

    private var weatherList: List<Weather> = listOf(Weather("Moscow", 15), Weather("Питер", 0), Weather("Воркута", 45))

    fun getWeather(): List<Weather> {
        return weatherList;
    }

    fun printStrings(vararg strings: String) {
        for(s in strings) {
            println(s)
        }
        for(s in 0..10) {
            println(s)
        }

        for(s in 10 downTo 1 step 2) {
            println(s)
        }

        for(s in 0 until weatherList.size) {
            println(weatherList[s].town + " " + weatherList[s].temperature)
        }
    }

//    fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
//        holder.bind(weatherList[position])
//    }
//
//    override fun getItemCount() = weatherList.size
}