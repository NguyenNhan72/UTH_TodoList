package com.example.uth_todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UthPrimaryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = text, fontSize = 16.sp, color = BackgroundWhite)
    }
}

@Composable
fun UthPageIndicator(pageCount: Int, currentPage: Int) {
    Row(
        modifier = Modifier.padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPage == iteration) PrimaryBlue else IndicatorGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(if (currentPage == iteration) 24.dp else 8.dp, 8.dp)
            )
        }
    }
}