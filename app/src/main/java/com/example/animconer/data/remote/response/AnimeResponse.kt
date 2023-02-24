package com.example.animconer.data.remote.response


import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val anime: List<Anime>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: Pagination
) {
    data class Anime(
        @SerializedName("aired")
        val aired: Aired,
        @SerializedName("airing")
        val airing: Boolean,
        @SerializedName("approved")
        val approved: Boolean,
        @SerializedName("background")
        val background: String,
        @SerializedName("broadcast")
        val broadcast: Broadcast,
        @SerializedName("demographics")
        val demographics: List<Demographic>,
        @SerializedName("duration")
        val duration: String,
        @SerializedName("episodes")
        val episodes: Int,
        @SerializedName("explicit_genres")
        val explicitGenres: List<String?>,
        @SerializedName("favorites")
        val favorites: Int,
        @SerializedName("genres")
        val genres: List<GenresResponse.Genre>,
        @SerializedName("images")
        val images: Images,
        @SerializedName("licensors")
        val licensors: List<Licensor>,
        @SerializedName("mal_id")
        val malId: Int,
        @SerializedName("members")
        val members: Int,
        @SerializedName("popularity")
        val popularity: Int,
        @SerializedName("producers")
        val producers: List<Producer>,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("rating")
        val rating: String,
        @SerializedName("score")
        val score: Double,
        @SerializedName("scored_by")
        val scoredBy: Int,
        @SerializedName("season")
        val season: String,
        @SerializedName("source")
        val source: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("studios")
        val studios: List<Studio>,
        @SerializedName("synopsis")
        val synopsis: String,
        @SerializedName("themes")
        val themes: List<Theme>,
        @SerializedName("title")
        val title: String,
        @SerializedName("title_english")
        val titleEnglish: String,
        @SerializedName("title_japanese")
        val titleJapanese: String,
        @SerializedName("title_synonyms")
        val titleSynonyms: List<String>,
        @SerializedName("titles")
        val titles: List<Title>,
        @SerializedName("trailer")
        val trailer: Trailer,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("year")
        val year: Int
    ) {
        data class Aired(
            @SerializedName("from")
            val from: String,
            @SerializedName("prop")
            val prop: Prop,
            @SerializedName("string")
            val string: String,
            @SerializedName("to")
            val to: String
        ) {
            data class Prop(
                @SerializedName("from")
                val from: From,
                @SerializedName("to")
                val to: To
            ) {
                data class From(
                    @SerializedName("day")
                    val day: Int,
                    @SerializedName("month")
                    val month: Int,
                    @SerializedName("year")
                    val year: Int
                )

                data class To(
                    @SerializedName("day")
                    val day: Int,
                    @SerializedName("month")
                    val month: Int,
                    @SerializedName("year")
                    val year: Int
                )
            }
        }

        data class Broadcast(
            @SerializedName("day")
            val day: String,
            @SerializedName("string")
            val string: String,
            @SerializedName("time")
            val time: String,
            @SerializedName("timezone")
            val timezone: String
        )

        data class Demographic(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )

        data class Genre(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )

        data class Images(
            @SerializedName("jpg")
            val jpg: Jpg,
            @SerializedName("webp")
            val webp: Webp
        ) {
            data class Jpg(
                @SerializedName("image_url")
                val imageUrl: String,
                @SerializedName("large_image_url")
                val largeImageUrl: String,
                @SerializedName("small_image_url")
                val smallImageUrl: String
            )

            data class Webp(
                @SerializedName("image_url")
                val imageUrl: String,
                @SerializedName("large_image_url")
                val largeImageUrl: String,
                @SerializedName("small_image_url")
                val smallImageUrl: String
            )
        }

        data class Licensor(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )

        data class Producer(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )

        data class Studio(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )

        data class Theme(
            @SerializedName("mal_id")
            val malId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        )

        data class Title(
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String
        )

        data class Trailer(
            @SerializedName("embed_url")
            val embedUrl: String,
            @SerializedName("images")
            val images: Images,
            @SerializedName("url")
            val url: String,
            @SerializedName("youtube_id")
            val youtubeId: String
        ) {
            data class Images(
                @SerializedName("image_url")
                val imageUrl: String,
                @SerializedName("large_image_url")
                val largeImageUrl: String,
                @SerializedName("maximum_image_url")
                val maximumImageUrl: String,
                @SerializedName("medium_image_url")
                val mediumImageUrl: String,
                @SerializedName("small_image_url")
                val smallImageUrl: String
            )
        }
    }

    data class Links(
        @SerializedName("first")
        val first: String,
        @SerializedName("last")
        val last: String,
        @SerializedName("next")
        val next: String,
        @SerializedName("prev")
        val prev: String?
    )

    data class Meta(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("from")
        val from: Int,
        @SerializedName("last_page")
        val lastPage: Int,
        @SerializedName("links")
        val links: List<Link>,
        @SerializedName("path")
        val path: String,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("to")
        val to: Int,
        @SerializedName("total")
        val total: Int
    ) {
        data class Link(
            @SerializedName("active")
            val active: Boolean,
            @SerializedName("label")
            val label: String,
            @SerializedName("url")
            val url: String
        )
    }

    data class Pagination(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("has_next_page")
        val hasNextPage: Boolean,
        @SerializedName("items")
        val items: Items,
        @SerializedName("last_visible_page")
        val lastVisiblePage: Int
    ) {
        data class Items(
            @SerializedName("count")
            val count: Int,
            @SerializedName("per_page")
            val perPage: Int,
            @SerializedName("total")
            val total: Int
        )
    }
}