package com.recipefinder.app.model

data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val cookingTime: String,
    val difficulty: String,
    val rating: Double,
    val reviewsCount: String,
    val isTrending: Boolean = false,
    val category: String = "All",
    val badges: List<String> = emptyList()
)
