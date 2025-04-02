package com.jvitor.jpcpratice.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jvitor.jpcpratice.ui.view.components.Home
import com.jvitor.jpcpratice.ui.view.components.PrimaryScreen
import com.jvitor.jpcpratice.ui.view.components.SecondScreen
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel

@Composable
fun NavGraph() {

    val viewModel: ScreenViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            Home(viewModel, navController)
            viewModel.recuperarDestaque()
            viewModel.recuperarPostagem()
        }
        composable("primary") {
            PrimaryScreen(navController)
        }
        composable("second") {
            SecondScreen(navController)
        }
    }
}