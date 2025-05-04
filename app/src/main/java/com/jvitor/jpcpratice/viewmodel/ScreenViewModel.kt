package com.jvitor.jpcpratice.viewmodel

import androidx.lifecycle.ViewModel
import com.jvitor.jpcpratice.R
import com.jvitor.jpcpratice.data.model.Feature
import com.jvitor.jpcpratice.data.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenViewModel : ViewModel() {

    private val _post = MutableStateFlow<List<Post>>(emptyList())
    val post: StateFlow<List<Post>> = _post.asStateFlow()

    private val _featured = MutableStateFlow<List<Feature>>(emptyList())
    val featured: StateFlow<List<Feature>> = _featured.asStateFlow()

    fun getPost(){
        val listPost = listOf(
            Post(R.drawable.perfil_01, "João", R.drawable.android, "Androidão é daora demais!", 2),
            Post(R.drawable.perfil_02, "Luiz", R.drawable.floresta, "Visitei a floresta e foi muito Legal!"),
            Post(R.drawable.perfil_03, "Luana", R.drawable.praia, "Visitei a praia e foi muito daora!", 2),
            Post(R.drawable.perfil_01, "Zyon", R.drawable.android, "Android é bom demais!", 3),
        )
        _post.value = listPost
    }

    fun getFeatured(){
        val listFeatured = listOf(
            Feature(profilePhoto = R.drawable.perfil_01, name = "João"),
            Feature(profilePhoto = R.drawable.perfil_02, name = "Luiz"),
            Feature(profilePhoto = R.drawable.perfil_03, name = "Luana"),
            Feature(profilePhoto = R.drawable.perfil_01, name = "Zyon"),
            Feature(profilePhoto = R.drawable.perfil_02, name = "Vitor"),
            Feature(profilePhoto = R.drawable.perfil_03, name = "Duarte"),
            Feature(profilePhoto = R.drawable.perfil_01, name = "Avelar"),
        )
        _featured.value = listFeatured
    }
}