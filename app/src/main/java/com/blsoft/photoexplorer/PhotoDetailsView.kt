package com.blsoft.photoexplorer

interface PhotoDetailsView {
    fun onDetailsLoaded(photoModel: PhotoModel)
    fun onDetailsNotLoaded()
}