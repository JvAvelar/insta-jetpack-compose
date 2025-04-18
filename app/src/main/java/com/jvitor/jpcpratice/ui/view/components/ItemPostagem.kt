package com.jvitor.jpcpratice.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jvitor.jpcpratice.R
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel


@Composable
fun ItemPostagem(
    viewModel: ScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val listaPostagem = viewModel.postagem.collectAsState().value
    LazyColumn(modifier.padding(top = 8.dp, bottom = 8.dp)) {
        items(listaPostagem) { post ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center

            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(post.fotoPerfil),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.LightGray, CircleShape)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = post.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, end = 220.dp),
                    )

                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = null,
                        )
                    }
                }

                Image(
                    painter = painterResource(post.fotoPost),
                    contentDescription = null
                )

                // Icones
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    var isLiked by remember { mutableStateOf(false) }
                    var isSalved by remember { mutableStateOf(false) }

                    IconButton(onClick = { isLiked = !isLiked }) {
                        Icon(
                            painter = if (isLiked) painterResource(R.drawable.ic_favorite_red)
                            else  painterResource(R.drawable.ic_favorite_empty),
                            contentDescription = if (isLiked) "Descutir" else "Curtir",
                            tint = if (isLiked) Color.Red else Color.Unspecified

                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_message),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_send),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = { isSalved = !isSalved },
                        modifier = Modifier.padding(start = 180.dp)
                    ) {
                        Icon(
                            painter = if (!isSalved) painterResource(R.drawable.ic_save_empty)
                            else painterResource(R.drawable.ic_save),
                            contentDescription = if (isSalved) "Não salvo" else "Salvo",

                        )
                    }
                }

                Row {
                    Text(
                        text = post.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Text(
                        text = post.descricao,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = "Há ${post.horario} horas",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ItemPostagemPreview() {
    ItemPostagem(viewModel(), rememberNavController())
}