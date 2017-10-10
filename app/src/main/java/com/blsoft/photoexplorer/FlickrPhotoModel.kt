package com.blsoft.photoexplorer

/**
 * Created by developer on 10/9/17.
 */
class FlickrPhotoModel(val photoId: String, val farm: Int, val server: String, val secret: String) : PhotoModel {

    override fun getThumbnailUrl(): String =
            "https://farm$farm.staticflickr.com/$server/${photoId}_${secret}_q.jpg"

}