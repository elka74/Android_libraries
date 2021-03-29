package com.example.android_libraries.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose

@Parcelize
data class GithubRepositories(
    @Expose val id: String? = null,
    @Expose val name:String? = null
): Parcelable