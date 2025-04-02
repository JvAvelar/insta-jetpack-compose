package com.jvitor.jpcpratice.viewmodel

import androidx.lifecycle.ViewModel
import com.jvitor.jpcpratice.R
import com.jvitor.jpcpratice.data.model.Destaque
import com.jvitor.jpcpratice.data.model.Postagem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenViewModel : ViewModel() {

    private val _postagem = MutableStateFlow<List<Postagem>>(emptyList())
    val postagem: StateFlow<List<Postagem>> = _postagem.asStateFlow()

    private val _destaque = MutableStateFlow<List<Destaque>>(emptyList())
    val destaque: StateFlow<List<Destaque>> = _destaque.asStateFlow()

    fun recuperarPostagem(){
        val listaPostagem = listOf(
            Postagem(R.drawable.perfil_01, "João", R.drawable.android, "Androidão é daora demais!", 2),
            Postagem(
                R.drawable.perfil_02,
                "Luiz",
                R.drawable.floresta,
                "Visitei a floresta e foi muito Legal!"
            ),
            Postagem(
                R.drawable.perfil_03,
                "Luana",
                R.drawable.praia,
                "Visitei a praia e foi muito daora!",
                2
            ),
            Postagem(R.drawable.perfil_01, "Zyon", R.drawable.android, "Android é bom demais!", 3),
        )
        _postagem.value = listaPostagem
    }

    fun recuperarDestaque(){
        val listaDestaques = listOf(
            Destaque(R.drawable.perfil_01, "João"),
            Destaque(R.drawable.perfil_02, "Luiz"),
            Destaque(R.drawable.perfil_03, "Luana"),
            Destaque(R.drawable.perfil_01, "Zyon"),
            Destaque(R.drawable.perfil_02, "Vitor"), Destaque(R.drawable.perfil_03, "Duarte"),
            Destaque(R.drawable.perfil_01, "Avelar"),
        )
        _destaque.value = listaDestaques
    }
}