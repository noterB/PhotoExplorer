package com.blsoft.photoexplorer

/**
 * Created by developer on 10/9/17.
 */
class PhotosPresenter(private val view: PhotosView, private val photosRepository: PhotosRepository) {

    fun getLastQuery() {
        val lastQuery = photosRepository.getLastQuery()
        view.onLastQuery(lastQuery)
    }

    fun getPhotosFor(query:String) {
        val photos = photosRepository.getPhotosFor(query)
        view.onPhotoList(photos)
    }

}