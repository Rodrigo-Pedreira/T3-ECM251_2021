package models;


data class Review (
    val id : Int,
    val idUser : Int,
    val idFilm : Int,
    val review : String,
    val likes : Int,
    val score : Double,
    val data : String,
)