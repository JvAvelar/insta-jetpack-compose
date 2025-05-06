package com.jvitor.jpcpratice.ui.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jvitor.jpcpratice.ui.view.components.BottomBar
import com.jvitor.jpcpratice.ui.view.components.Featured
import com.jvitor.jpcpratice.ui.view.components.Post
import com.jvitor.jpcpratice.ui.view.components.TopBar
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

            Featured(viewModel)

            HorizontalDivider(modifier = Modifier.height(2.dp))

            Post(viewModel, navController, Modifier)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomePreview() {
    Home(viewModel(), rememberNavController())
}