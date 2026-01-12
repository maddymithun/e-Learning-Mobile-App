package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.primaryColor
import com.example.composecleanarchitecture.ui.theme.robotoFont
import com.example.composecleanarchitecture.ui.theme.whiteColor


@Composable
fun ToolBarLayoutComponent(
    navController: NavController,
    isBackShow: Boolean = true,
    title: String,
    textSize: TextUnit = 16.sp
) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .background(color = primaryColor)
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            if (isBackShow) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = whiteColor,
                    modifier = Modifier.padding(end = 15.dp).clickable {
                        navController.popBackStack()
                    })
            }
            Text(
                text = title,
                fontFamily = robotoFont,
                fontWeight = FontWeight.Normal,
                color = whiteColor,
                fontSize = textSize
            )
        }
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = "Dark",
                fontFamily = robotoFont,
                fontWeight = FontWeight.Normal,
                color = whiteColor,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                modifier = Modifier
                    .height(20.dp)
                    .width(40.dp)
                    .scale(0.8f),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = activeTextColor,
                    checkedTrackColor = whiteColor,
                    uncheckedThumbColor = activeTextColor,
                    uncheckedTrackColor = whiteColor,
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Light",
                fontFamily = robotoFont,
                fontWeight = FontWeight.Normal,
                color = whiteColor,
                fontSize = 10.sp
            )

            Icon(
                Icons.Default.Notifications, contentDescription = null,
                modifier = Modifier.padding(start = 25.dp),
                tint = whiteColor
            )
            Icon(
                Icons.Default.MoreVert, contentDescription = null,
                modifier = Modifier.padding(start = 10.dp),
                tint = whiteColor
            )
        }
    }
}

@Preview
@Composable
fun preview3() {
    ToolBarLayoutComponent(
        navController = rememberNavController(),
        isBackShow = false,
        title = "রাফখাতা"
    )
}