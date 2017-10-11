package com.blsoft.photoexplorer


interface PhotosRepository {
    fun getLastQuery():String
    fun getPhotoModelFor(photoId: String): PhotoModel?
    fun getPhotosFor(query: String): List<PhotoModel>
    fun loadDetailsFor(photoModel: PhotoModel): PhotoModel
}