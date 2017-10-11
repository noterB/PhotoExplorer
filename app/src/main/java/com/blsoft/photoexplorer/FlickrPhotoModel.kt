package com.blsoft.photoexplorer

class FlickrPhotoModel(override val photoId: String,
                       val farm: Int,
                       val server: String,
                       val secret: String) : PhotoModel {

    override var photographer: String = ""
    override var location: String = ""
    override var title: String = ""

    override fun getUrl(): String =
            "https://farm$farm.staticflickr.com/$server/${photoId}_${secret}_z.jpg"

    override fun getThumbnailUrl(): String =
            "https://farm$farm.staticflickr.com/$server/${photoId}_${secret}_q.jpg"

}