package com.jvitor.jpcpratice.data.model

data class Post(
    val profilePhoto: Int,
    val name: String,
    val postPhoto: Int,
    val description: String,
    val time: Int = 1
)
