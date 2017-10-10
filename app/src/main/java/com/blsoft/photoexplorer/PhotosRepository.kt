package com.blsoft.photoexplorer


interface PhotosRepository {
    fun getLastQuery():String
    fun getPhotosFor(query: String): List<PhotoModel>
}