package models;

import kotlinx.serialization.Serializable

@Serializable

data class Films(
    val id : Int,
    val name : String,
    val genre : String,
    val director : String,
    val date : String
)