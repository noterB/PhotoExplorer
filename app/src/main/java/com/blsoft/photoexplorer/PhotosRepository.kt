package com.blsoft.photoexplorer


interface PhotosRepository {
    fun getPhotosFor(query: String): List<PhotoModel>
}