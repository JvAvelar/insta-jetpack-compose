package com.jvitor.jpcpratice.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel

@Composable
fun Home(
    viewModel: ScreenViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopBar(navController)
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            ItemDestaque(viewModel, navController, Modifier)

            ItemPostagem(viewModel, navController, Modifier)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomePreview() {

    //ItemDestaque()
    Home(viewModel(), rememberNavController())

}