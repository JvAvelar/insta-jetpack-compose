package com.jvitor.jpcpratice.data.model

data class PostModel(
    val profilePhoto: Int,
    val name: String,
    val postPhoto: Int,
    val description: String,
    val time: Int = 1
)
