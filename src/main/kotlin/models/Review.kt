package models;

import kotlinx.serialization.Serializable

@Serializable
data class Review (
    val id : Int,
    val idUser : Int,
    val idFilm : Int,
    val review : String,
    val likes : Int,
    val score : Double,
    val date : String,
)
