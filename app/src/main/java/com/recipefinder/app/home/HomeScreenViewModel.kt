package com.recipefinder.app.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipefinder.app.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query
data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

data class MealResponse(val meals: List<MealDto>?)

interface MealApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse
}

class RecipeRepository(private val apiService: MealApiService) {
    suspend fun getRecipesByCategory(category: String): Result<List<Recipe>> {
        return try {
            val response = apiService.getMealsByCategory(category)
            val domainList = response.meals?.map { dto ->
                Recipe(
                    id = dto.idMeal,
                    title = dto.strMeal,
                    imageUrl = dto.strMealThumb,
//                    prepTime = "20 min", // Mock value (API doesn't return time)
                    rating = 4.8,
                )
            } ?: emptyList()
            Result.success(domainList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val recipes: List<Recipe>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

class HomeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadRecipes("Dessert")
    }

    fun loadRecipes(category: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            repository.getRecipesByCategory(category)
                .onSuccess { recipes -> _uiState.value = HomeUiState.Success(recipes) }
                .onFailure { _uiState.value = HomeUiState.Error(it.localizedMessage ?: "Error") }
        }
    }
}