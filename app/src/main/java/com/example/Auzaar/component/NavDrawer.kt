package com.example.Auzaar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Auzaar.R
import com.example.Auzaar.ui.theme.oswald

private val screens = listOf("Sell", "My Account", "Sign Out")

@Composable
fun NavDrawer(modifier: Modifier = Modifier, onSignOutClicked: () -> Unit) {
    Column(
        modifier
            .fillMaxSize()

    ) {
        Card(
            modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(width = 2.dp, color = Color.Black),
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            Text(
                text = "Your name",
                modifier.padding(start = 10.dp, top = 140.dp),
                style = MaterialTheme.typography.h1.copy(fontFamily = oswald, fontSize = 30.sp),
                textAlign = TextAlign.Start
            )
        }

        for (screen in screens) {
            Card(
                modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .border(width = 0.5.dp, color = Color.Black)
                    .clickable {if (screen == "Sign Out"){
                        onSignOutClicked()
                    } }) {
                Text(
                    text = screen,
                    modifier.padding(10.dp),
                    style = MaterialTheme.typography.h3.copy(fontFamily = oswald, fontSize = 20.sp)
                )
            }
        }

    }
}