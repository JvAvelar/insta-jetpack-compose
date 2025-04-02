package com.jvitor.jpcpratice.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jvitor.jpcpratice.R
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel

@Composable
fun ItemDestaque(
    viewModel: ScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val listaDestaque = viewModel.destaque.collectAsState().value
    LazyRow(modifier){
        items(listaDestaque) { destaque ->
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(destaque.fotoPerfil),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.LightGray, CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = destaque.name,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
