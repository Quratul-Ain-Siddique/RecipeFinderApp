package com.recipefinder.app.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.recipefinder.app.ui.theme.DeepNavy
import com.recipefinder.app.ui.theme.PrimaryBlue
import com.recipefinder.app.ui.theme.TextPrimary

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Icon (Restaurant/Logo)
        Surface(
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.05f),
            modifier = Modifier.size(48.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
        ) {
            Icon(
                imageVector = Icons.Outlined.Restaurant,
                contentDescription = null,
                tint = PrimaryBlue,
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // App Title & Current Screen
        Column {
            Text(
                text = "RECIPE FINDER",
                color = PrimaryBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Text(
                text = "Home",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Search Icon
        Surface(
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.05f),
            modifier = Modifier.size(48.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Profile Image
        Surface(
            shape = CircleShape,
            modifier = Modifier.size(48.dp),
            border = BorderStroke(2.dp, PrimaryBlue.copy(alpha = 0.4f))
        ) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=100",
                contentDescription = "Profile",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    Surface(color = DeepNavy) {
        HomeTopBar()
    }
}