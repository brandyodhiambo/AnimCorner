package com.example.animconer.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.animconer.model.Genre
import com.example.animconer.model.Images
import com.example.animconer.model.Producer
import com.example.animconer.model.Trailer
import com.example.animconer.model.characters.CharacterData
import com.example.animconer.model.characters.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converter(private val gson: Gson) {
    @TypeConverter
    fun toGenreString(parts: List<Genre>): String {
        return gson.toJson(
            parts,
            object : TypeToken<ArrayList<Genre>>() {}.type,
        ) ?: "[]"
    }

    @TypeConverter
    fun toGenres(parts: String): List<Genre> {
        return gson.fromJson<ArrayList<Genre>>(
            parts,
            object : TypeToken<ArrayList<Genre>>() {}.type,
        ) ?: emptyList()
    }

    @TypeConverter
    fun convertFromImagesToJSONString(images: Images): String {
        return Gson().toJson(images)
    }

    @TypeConverter
    fun convertFromJSOnStringToImages(jsonToConvert: String): Images {
        return Gson().fromJson(jsonToConvert, Images::class.java)
    }

    @TypeConverter
    fun fromProducer(producer: List<Producer>): String {
        return gson.toJson(
            producer,
            object : TypeToken<ArrayList<Producer>>() {}.type,
        ) ?: "[]"
    }

    @TypeConverter
    fun toProducer(producer: String): List<Producer> {
        return gson.fromJson<ArrayList<Producer>>(
            producer,
            object : TypeToken<ArrayList<Producer>>() {}.type,
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromListOfCharacter(characters: List<CharacterData>): String {
        return gson.toJson(
            characters,
            object : TypeToken<ArrayList<CharacterData>>() {}.type,
        ) ?: "[]"
    }

    @TypeConverter
    fun toListOfCharacter(characters: String): List<CharacterData> {
        return gson.fromJson<ArrayList<CharacterData>>(
            characters,
            object : TypeToken<ArrayList<CharacterData>>() {}.type,
        ) ?: emptyList()
    }

    @TypeConverter
    fun convertFromTrailersToJSONString(trailer: Trailer): String {
        return Gson().toJson(trailer)
    }

    @TypeConverter
    fun convertFromJSOnStringToTrailer(jsonToConvert: String): Trailer {
        return Gson().fromJson(jsonToConvert, Trailer::class.java)
    }

    @TypeConverter
    fun convertFromPersonToJSONString(person: Person): String {
        return Gson().toJson(person)
    }

    @TypeConverter
    fun convertFromJSOnStringToPerson(jsonToConvert: String): Person {
        return Gson().fromJson(jsonToConvert, Person::class.java)
    }

    @TypeConverter
    fun fromGenres(json: String): List<String> {
        return gson.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type,
        ) ?: emptyList()
    }

    @TypeConverter
    fun toGenres(ingredients: List<String>): String {
        return gson.toJson(
            ingredients,
            object : TypeToken<ArrayList<String>>() {}.type,
        ) ?: "[]"
    }
}
