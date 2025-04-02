package com.jvitor.jpcpratice.ui.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SecondScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .padding(top = 32.dp, start = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            fontSize = 20.sp,
            text = "Second Screen",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = {
            navController.navigate("home")
        }) {
            Text(
                fontSize = 20.sp,
                text = "next"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SecondScreenPreview() {
    SecondScreen(rememberNavController())
}