package com.jvitor.jpcpratice.viewmodel

import androidx.lifecycle.ViewModel
import com.jvitor.jpcpratice.R
import com.jvitor.jpcpratice.data.model.FeatureModel
import com.jvitor.jpcpratice.data.model.PostModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenViewModel : ViewModel() {

    private val _post = MutableStateFlow<List<PostModel>>(emptyList())
    val post: StateFlow<List<PostModel>> = _post.asStateFlow()

    private val _featured = MutableStateFlow<List<FeatureModel>>(emptyList())
    val featured: StateFlow<List<FeatureModel>> = _featured.asStateFlow()

    fun getPost(){
        val listPost = listOf(
            PostModel(R.drawable.perfil_01, "João", R.drawable.android, "Androidão é daora demais!", 2),
            PostModel(R.drawable.perfil_02, "Luiz", R.drawable.floresta, "Visitei a floresta e foi muito Legal!"),
            PostModel(R.drawable.perfil_03, "Luana", R.drawable.praia, "Visitei a praia e foi muito daora!", 2),
            PostModel(R.drawable.perfil_01, "Zyon", R.drawable.android, "Android é bom demais!", 3),
        )
        _post.value = listPost
    }

    fun getFeatured(){
        val listFeatured = listOf(
            FeatureModel(profilePhoto = R.drawable.perfil_01, name = "João"),
            FeatureModel(profilePhoto = R.drawable.perfil_02, name = "Luiz"),
            FeatureModel(profilePhoto = R.drawable.perfil_03, name = "Luana"),
            FeatureModel(profilePhoto = R.drawable.perfil_01, name = "Zyon"),
            FeatureModel(profilePhoto = R.drawable.perfil_02, name = "Vitor"),
            FeatureModel(profilePhoto = R.drawable.perfil_03, name = "Duarte"),
            FeatureModel(profilePhoto = R.drawable.perfil_01, name = "Avelar"),
        )
        _featured.value = listFeatured
    }
}