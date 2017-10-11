package com.blsoft.photoexplorer

class PhotoDetailsPresenter(val view:PhotoDetailsView, val repository: PhotosRepository) {

    fun getDetails(photoId: String?) {
        if (photoId == null) {
            view.onDetailsNotLoaded()
            return
        }
        val model = repository.getPhotoModelFor(photoId)
        if (model == null) {
            view.onDetailsNotLoaded()
            return
        }
        view.onDetailsLoaded(repository.loadDetailsFor(model))
    }


}