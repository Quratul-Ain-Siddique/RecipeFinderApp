package com.recipefinder.app.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.recipefinder.app.model.Recipe
import com.recipefinder.app.ui.theme.PrimaryBlue
import com.recipefinder.app.ui.theme.SurfaceDark
import com.recipefinder.app.ui.theme.TextPrimary
import com.recipefinder.app.ui.theme.TextSecondary

@Composable
fun RecommendedRecipeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Surface(
        color = SurfaceDark,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(100.dp)) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "View Recipe",
                        color = PrimaryBlue,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecommendedRecipeItemPreview() {
    RecommendedRecipeItem(
        recipe = Recipe(
            id = "1",
            title = "Matcha Chia Pudding",
            imageUrl = "",
        )
    )
}
