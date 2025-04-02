package com.jvitor.jpcpratice.data.model

data class Postagem(
    val fotoPerfil: Int,
    val name: String,
    val fotoPost: Int,
    val descricao: String,
    val horario: Int = 1
)
