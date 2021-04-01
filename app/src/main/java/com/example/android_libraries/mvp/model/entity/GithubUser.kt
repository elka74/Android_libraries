package com.example.android_libraries.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose
import io.reactivex.rxjava3.core.SingleSource


@Parcelize
data class GithubUser(
    @Expose val id: String,
    @Expose val login: String,
    @Expose val avatarUrl: String,
    @Expose val reposUrl: String? = null
) : Parcelable