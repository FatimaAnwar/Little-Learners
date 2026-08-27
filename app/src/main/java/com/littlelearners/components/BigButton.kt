package com.littlelearners.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BigButton(
    text: String,
    icon: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,

        modifier =
            Modifier
                .fillMaxWidth()
                .height(80.dp),

        shape =
            RoundedCornerShape(28.dp),

        border =
            BorderStroke(
                4.dp,
                Color.White
            ),

        colors =
            ButtonDefaults.buttonColors(
                containerColor =
                    Color(0xFF6C63FF)
            )
    ) {

        Text(
            text = "$icon  $text",
            fontSize = 23.sp
        )
    }
}