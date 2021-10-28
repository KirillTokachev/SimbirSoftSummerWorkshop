package com.example.simbirsoftsummerworkshop.model

import java.time.LocalDate

data class User(
    val name: String,
    val dateOfBirth: LocalDate,
    val profession: String,
    var avatar: Int,
    val friends: List<User>,
    val push: Boolean = false
)
