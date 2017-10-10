package com.blsoft.photoexplorer

/**
 * Created by developer on 10/9/17.
 */
class PhotosPresenter(val view: PhotosView, val photosRepository: PhotosRepository) {

    fun getPhotosFor(query:String) {
        val photos = photosRepository.getPhotosFor(query)
        view.onPhotoList(photos)
    }

}