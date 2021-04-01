package com.example.android_libraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
    @PrimaryKey var id: String,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String? = null
)