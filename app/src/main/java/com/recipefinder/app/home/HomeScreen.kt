package com.recipefinder.app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.recipefinder.app.home.components.CategoryChip
import com.recipefinder.app.home.components.FeaturedRecipeCard
import com.recipefinder.app.home.components.HomeTopBar
import com.recipefinder.app.home.components.RecommendedRecipeItem
import com.recipefinder.app.ui.theme.BgColor
import com.recipefinder.app.ui.theme.PrimaryBlue
import com.recipefinder.app.ui.theme.TextPrimary

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
                    val apiService = MealApiService.create()
                    val repository = RecipeRepository(apiService)
                    @Suppress("UNCHECKED_CAST")
                    return HomeScreenViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedCategory by remember { mutableStateOf("Seafood") }

    val categories = listOf("Breakfast", "Beef", "Chicken", "Dessert", "Vegan", "Seafood")

    Scaffold(
        containerColor = BgColor,
        topBar = {
            HomeTopBar()
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState) {
                is HomeUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = PrimaryBlue
                    )
                }

                is HomeUiState.Success -> {
                    val recipes = state.recipes
                    val featuredRecipes = recipes.take(3)
                    val recommendedRecipes = recipes.drop(3)

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        // Featured Pager
                        if (featuredRecipes.isNotEmpty()) {
                            item {
                                LazyRow(
                                    contentPadding = PaddingValues(horizontal = 24.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    items(featuredRecipes) { recipe ->
                                        FeaturedRecipeCard(
                                            recipe = recipe,
                                            modifier = Modifier.width(300.dp)
                                        )
                                    }
                                }
                            }
                            item { Spacer(modifier = Modifier.height(32.dp)) }
                        }

                        // Categories
                        item {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 24.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(categories) { category ->
                                    CategoryChip(
                                        category = category,
                                        isSelected = selectedCategory == category,
                                        onClick = {
                                            selectedCategory = category
                                            viewModel.loadRecipes(category)
                                        }
                                    )
                                }
                            }
                        }

                        item { Spacer(modifier = Modifier.height(24.dp)) }

                        // Recommended Header
                        item {
                            Text(
                                text = "Recommended",
                                style = MaterialTheme.typography.titleLarge,
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                        }

                        item { Spacer(modifier = Modifier.height(16.dp)) }

                        // Recommended List
                        items(recommendedRecipes) { recipe ->
                            RecommendedRecipeItem(
                                recipe = recipe,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

                is HomeUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
