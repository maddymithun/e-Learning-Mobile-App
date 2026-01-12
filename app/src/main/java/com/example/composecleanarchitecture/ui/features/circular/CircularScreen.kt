package com.example.composecleanarchitecture.ui.features.circular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.R


@Composable
fun CircularScreen(
    logout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
    ) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = "Settings",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 20.sp
            )
            OutlinedButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = { logout() }
            ) {
                Text("Logout")
            }
        }
    }
}