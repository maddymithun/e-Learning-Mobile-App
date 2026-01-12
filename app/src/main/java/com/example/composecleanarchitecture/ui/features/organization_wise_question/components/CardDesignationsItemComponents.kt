package com.example.composecleanarchitecture.ui.features.organization_wise_question.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.models.designations.DesignationDataModel
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.carosoulBgColor
import com.example.composecleanarchitecture.ui.theme.robotoFont
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun CardDesignationsItemComponents(
    desgItem: DesignationDataModel,
    onOrganizationItemClick: (DesignationDataModel) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = carosoulBgColor),
        modifier = Modifier
            .height(120.dp)
            .width(90.dp)
            .clickable {
                onOrganizationItemClick.invoke(desgItem)
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_trace_disable),
                    contentDescription = null, modifier = Modifier.padding(start = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(end = 5.dp, top = 5.dp)
                        .clip(shape = RoundedCornerShape(percent = 50))
                        .background(color = activeTextColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "New",
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        fontFamily = robotoFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 7.sp,
                        color = whiteColor
                    )
                }
            }
            Spacer(modifier = Modifier.weight(.1f))
            CustomTextComponents(
                title = "${desgItem.name}",
                textSize = 12.sp,
                paddingTop = 10.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp,
                lineHeight = 16.sp,
                paddingBottom = 5.dp
            )
            Spacer(modifier = Modifier.weight(.1f))
        }
    }
}