package com.example.marvel_2.data.datasource.local

import androidx.room.TypeConverter
import com.example.marvel_2.domain.model.Personagem
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun converterFromPersonagem(value: String): Personagem? {
        return Gson().fromJson(value, Personagem::class.java)
    }

    @TypeConverter
    fun converterToJson(char: Personagem): String {
        return Gson().toJson(char)
    }
}