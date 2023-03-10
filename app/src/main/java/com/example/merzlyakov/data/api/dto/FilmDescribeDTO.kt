package com.example.merzlyakov.data.api.dto

import com.example.merzlyakov.domain.model.FilmDescribe

data class FilmDescribeDTO(
    val completed: Boolean,
    val countries: List<Country>,
    val coverUrl: String,
    val description: String,
    val editorAnnotation: Any,
    val endYear: Any,
    val filmLength: Int,
    val genres: List<Genre>,
    val has3D: Boolean,
    val hasImax: Boolean,
    val imdbId: Any,
    val isTicketsAvailable: Boolean,
    val kinopoiskId: Int,
    val lastSync: String,
    val logoUrl: Any,
    val nameEn: Any,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val productionStatus: Any,
    val ratingAgeLimits: String,
    val ratingAwait: Any,
    val ratingAwaitCount: Int,
    val ratingFilmCritics: Double,
    val ratingFilmCriticsVoteCount: Int,
    val ratingGoodReview: Any,
    val ratingGoodReviewVoteCount: Int,
    val ratingImdb: Double,
    val ratingImdbVoteCount: Int,
    val ratingKinopoisk: Double,
    val ratingKinopoiskVoteCount: Int,
    val ratingMpaa: String,
    val ratingRfCritics: Double,
    val ratingRfCriticsVoteCount: Int,
    val reviewsCount: Int,
    val serial: Boolean,
    val shortDescription: String,
    val shortFilm: Boolean,
    val slogan: String,
    val startYear: Any,
    val type: String,
    val webUrl: String,
    val year: Int
)

fun FilmDescribeDTO.getFilmDescribe(): FilmDescribe {
    return FilmDescribe(
        title = nameRu,
        describe = description,
        genres = genres.map { it.genre },
        country = countries.map { it.country },
        poster = posterUrl
    )
}