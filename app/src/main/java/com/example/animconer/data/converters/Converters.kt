package com.example.animconer.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.animconer.model.anime.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

@ProvidedTypeConverter
class Converter(private val gson: Gson){
    @TypeConverter
    fun toGenreString(parts:List<Genre>) : String{
        return gson.toJson(
            parts,
            object: TypeToken<ArrayList<Genre>>() {}.type
        ) ?: "[]"
    }
    @TypeConverter
    fun toGenres(parts:String):List<Genre>{
        return gson.fromJson<ArrayList<Genre>>(
            parts,
            object:TypeToken<ArrayList<Genre>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromJpg(images: Jpg): String {
        return JSONObject().apply {
            put("imageUrl", images.imageUrl)
            put("large_imageUrl",images.largeImageUrl)
            put("small_imageUrl",images.smallImageUrl)
        }.toString()
    }

    @TypeConverter
    fun toJpg(images: String): Jpg {
        val json = JSONObject(images)
        return Jpg(json.getString("jpg"),json.getString("large_imageUrl"),json.getString("small_imageUrl"))
    }


    @TypeConverter
    fun fromProducer(producer:List<Producer>) : String{
        return gson.toJson(
            producer,
            object: TypeToken<ArrayList<Producer>>() {}.type
        ) ?: "[]"
    }
    @TypeConverter
    fun toProducer(producer:String):List<Producer>{
        return gson.fromJson<ArrayList<Producer>>(
            producer,
            object:TypeToken<ArrayList<Producer>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromTrailer(trailer: Trailer): String {
        return JSONObject().apply {
            put("url", trailer.url)
            put("youtube_id",trailer.youtubeId)
        }.toString()
    }

    @TypeConverter
    fun toTrailer(trailer: String): Trailer {
        val json = JSONObject(trailer)
        return Trailer(json.getString("url"),json.getString("youtube_id"))
    }


}