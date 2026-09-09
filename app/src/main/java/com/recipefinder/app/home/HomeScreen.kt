package com.recipefinder.app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.recipefinder.app.home.components.CategoryChip
import com.recipefinder.app.home.components.FeaturedRecipeCard
import com.recipefinder.app.home.components.HomeTopBar
import com.recipefinder.app.home.components.RecommendedRecipeItem
import com.recipefinder.app.model.Recipe
import com.recipefinder.app.ui.theme.BgColor
import com.recipefinder.app.ui.theme.TextPrimary

@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = listOf("All", "Vegan", "Quick & Easy", "Desserts", "Breakfast")

    val featuredRecipes = remember {
        listOf(
            Recipe(
                id = "1",
                title = "Avocado Toast with Poached Egg",
                imageUrl = "https://images.unsplash.com/photo-1525351484163-7529414344d8?w=800",
                reviewsCount = "2.4k",
                isTrending = true
            ),
            Recipe(
                id = "2",
                title = "Creamy Garlic Chicken",
                imageUrl = "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?w=800",
                reviewsCount = "1.2k",
                isTrending = true
            )
        )
    }

    val recommendedRecipes = remember {
        listOf(
            Recipe(
                id = "3",
                title = "Matcha Chia Pudding",
                imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=800",
                reviewsCount = "94",
            ),
            Recipe(
                id = "4",
                title = "Crispy Lemon Herb Salmon",
                imageUrl = "https://images.unsplash.com/photo-1467003909585-2f8a72700288?w=800",
                reviewsCount = "312",
            ),
            Recipe(
                id = "5",
                title = "Fluffy Buttermilk Pancakes",
                imageUrl = "https://images.unsplash.com/photo-1528207776546-365bb710ee93?w=800",
                reviewsCount = "512",
            )
        )
    }

    Scaffold(
        containerColor = BgColor,
        topBar = {
            HomeTopBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Featured Pager
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(featuredRecipes) { recipe ->
                        FeaturedRecipeCard(recipe = recipe, modifier = Modifier.width(300.dp))
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

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
                            onClick = { selectedCategory = category }
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
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
