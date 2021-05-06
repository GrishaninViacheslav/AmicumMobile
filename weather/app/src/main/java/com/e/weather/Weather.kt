package com.e.weather

class Weather (val town: String, val temperature: Int) {
    companion object : Comparator<Weather> {
        override fun compare(o1: Weather, o2: Weather): Int {
            if (o1.temperature > o2.temperature) {
                return 1
            } else if (o1.temperature == o2.temperature) {
                return 0
            } else {
                return -1
            }
        }
    }
}
//class Weather {
//    val town: String = "";
//    val temperature: Int = 0;
//
//    fun setWeather(town: String,
//                   isHomeTown: Boolean = true,
//                   temperature: Int = 15) {
//
//    }
//}
