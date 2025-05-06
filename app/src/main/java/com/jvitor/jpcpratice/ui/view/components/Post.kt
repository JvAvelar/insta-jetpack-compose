package com.jvitor.jpcpratice.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jvitor.jpcpratice.R
import com.jvitor.jpcpratice.data.model.PostModel
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel


@Composable
fun Post(
    viewModel: ScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val listPost = viewModel.post.collectAsState().value
    Box {
        LazyColumn(
            modifier
                .testTag("lazy_column_post")
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            items(listPost) { post ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box {
                        ProfilePost(post, navController)
                    }

                    Box {
                        PhotoPostedPost(post)
                    }

                    Box {
                        IconsInteractionsPost()
                    }

                    Box {
                        DescriptionAndTimePost(post)
                    }
                }
            }
        }
    }
}

// Foto de perfil, nome do usuário e menu
@Composable
private fun ProfilePost(post: PostModel, navController: NavController) {
    Row(
        modifier = Modifier
            .testTag("row_main_profile_post")
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(post.profilePhoto),
            contentDescription = "profile photo",
            modifier = Modifier
                .testTag("img_profile_photo_post")
                .size(40.dp)
                .background(Color.LightGray, CircleShape)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = post.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 8.dp, end = 220.dp)
                .testTag("txt_profile_name_post")
        )

        IconButton(
            onClick = { navController.navigate("primary") },
            modifier = Modifier.testTag("btn_profile_menu_post")
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = "icon menu",
            )
        }
    }
}

// Foto postada
@Composable
private fun PhotoPostedPost(post: PostModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .testTag("box_main_photo_post")
    ) {
        Image(
            modifier = Modifier.testTag("img_posted_photo_post"),
            painter = painterResource(post.postPhoto),
            contentDescription = "post photo"
        )
    }
}

// Icones de interação: like, comentario e compartilhar
@Composable
private fun IconsInteractionsPost() {
    var isLiked by remember { mutableStateOf(false) }
    var isSalved by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.testTag("row_main_icons_post"),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { isLiked = !isLiked },
            modifier = Modifier.testTag("btn_like_post")
        ) {
            Icon(
                painter = if (isLiked) painterResource(R.drawable.ic_favorite_red)
                else painterResource(R.drawable.ic_favorite_empty),
                contentDescription = if (isLiked) "disliked" else "liked",
                tint = if (isLiked) Color.Red else Color.Unspecified
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier.testTag("btn_message_post")
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_message),
                contentDescription = "icon message"
            )
        }

        IconButton(onClick = {}, modifier = Modifier.testTag("icon_send_post")) {
            Icon(
                painter = painterResource(R.drawable.ic_send),
                contentDescription = "icon send"
            )
        }

        IconButton(
            onClick = { isSalved = !isSalved },
            modifier = Modifier
                .padding(start = 180.dp)
                .testTag("btn_save_post")
        ) {
            Icon(
                painter = if (!isSalved) painterResource(R.drawable.ic_save_empty)
                else painterResource(R.drawable.ic_save),
                contentDescription = if (isSalved) "no save" else "saved",
            )
        }
    }
}

// Nome, descrição e tempo da postagem
@Composable
private fun DescriptionAndTimePost(post: PostModel) {
    Column(modifier = Modifier.testTag("column_main_description_post")) {
        Row(modifier = Modifier.testTag("row_name_and_description_post")) {
            Text(
                text = post.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .testTag("txt_name_description_post")
            )

            Text(
                text = post.description,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .testTag("txt_description_post")
            )
        }

        Text(
            text = "Há ${post.time} horas",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 8.dp)
                .testTag("txt_time_post")
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PostPreview() {
    Post(viewModel(), rememberNavController())
}