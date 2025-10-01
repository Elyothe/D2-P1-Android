package com.example.d2_p1.admin.domain

data class Space(
    val id: String = "",
    val name: String,
    val description: String,
    val capacity: Int,
    val options: List<String>
)
