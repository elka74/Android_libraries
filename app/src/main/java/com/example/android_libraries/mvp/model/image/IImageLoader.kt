package com.example.android_libraries.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}