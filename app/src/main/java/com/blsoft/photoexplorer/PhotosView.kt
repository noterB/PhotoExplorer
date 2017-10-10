package com.blsoft.photoexplorer


interface PhotosView {
    fun onPhotoList(photos:List<PhotoModel>)
    fun onLastQuery(query: String)
}