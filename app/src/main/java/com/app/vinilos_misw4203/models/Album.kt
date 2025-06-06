package com.app.vinilos_misw4203.models

data class Album(
    val albumId: Int,
    val name: String,
    val coverUrl: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)
