package com.jvitor.jpcpratice.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jvitor.jpcpratice.navigation.NavGraph
import com.jvitor.jpcpratice.ui.theme.JPCpraticeTheme
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JPCpraticeTheme {
                NavGraph()
            }
        }
    }
}